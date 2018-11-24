<?php
class DatabaseMaster{
	private $databaseConnection;
	function __construct(){
		$this->connectDatabase();
	}




	private function connectDatabase(){
		$host = 'localhost';
		$user = 'root';
		$password = '';
		$db_name = 'beap';
		$this->databaseConnection = mysqli_connect($host, $user, $password, $db_name);
		mysqli_set_charset($this->databaseConnection, 'utf8');
			

	}
	public function querySelect($query){
		$result = mysqli_query($this->databaseConnection, $query);
		if($result){
			$resultData = array();
			$rowCount = mysqli_num_rows($result);
			if($rowCount >= 1){
				if($rowCount == 1)
				$resultData[] = mysqli_fetch_array($result, MYSQLI_ASSOC);
				else{
					while($rowData = mysqli_fetch_array($result, MYSQLI_ASSOC))
					$resultData[] = $rowData;
				}
				return $resultData;
			}
			else return $resultData;
		}
		else return false;
	}
	public function queryCount($query){
		$result = mysqli_query($this->databaseConnection, $query);
		$rowCount = mysqli_num_rows($result);
			
		return $rowCount;
	}
	public function querySearch($query, $rowName){
		$result = mysqli_query($this->databaseConnection, $query);
		$data = array();
		while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
			array_push($data, $row[$rowName]);
		}
		return $data;
	}

	public function queryUpdate($query){
		$result = mysqli_query($this->databaseConnection, $query);
		if($result)	return true;
		else return false;
	}
	public function escapeString($string){
		$string = mysqli_real_escape_string($this->databaseConnection, trim($string));
		return $string;
	}

	public function checkID($idNo){
		$query = "SELECT userID FROM users_info WHERE studentID='$idNo'";
		$result = mysqli_query($this->databaseConnection, $query);
		if($result){
			if(mysqli_num_rows($result)) return false;
			else return true;
		}
		else return false;
	}

	public function checkEmail($email){
		$query = "SELECT Email FROM tblusers WHERE Email='$email'";
		$result = mysqli_query($this->databaseConnection, $query);
		if($result){
			if(mysqli_num_rows($result)) return true;
			else return false;
		}
		else return true;
	}

	public function checkPassword($idNo, $password){
		$query = "SELECT Password FROM tblusers WHERE Password=SHA2('$password', 224) AND Email = '$idNo'";
		$result = mysqli_query($this->databaseConnection, $query);
		if($result){
			if(mysqli_num_rows($result)) return true;
			else return false;
		}
		else return false;
	}

	public function checkAdmin($username){
		$query = "SELECT userName FROM admin WHERE userName='$username'";
		$result = mysqli_query($this->databaseConnection, $query);
		if($result){
			if(mysqli_num_rows($result)) return false;
			else return true;
		}
		else return false;
	}

	public function checkUser($username, $password){
		$query = "SELECT Password FROM tblusers WHERE Password=SHA2('$password',224) AND Email = '$username'";
		$result = mysqli_query($this->databaseConnection, $query);
		if($result){
			if(mysqli_num_rows($result)) return true;
			else return false;
		}
		else return false;
	}

	public function insertedID()
{
	return $this->databaseConnection->insert_id;
}

	
public function currentDateTime()
{
	date_default_timezone_set('Asia/Manila');
return date('Y-m-d H:i:s');
}

public function convertMonthNum($num_month)
{
    $dateObj   = DateTime::createFromFormat('!m', $num_month);
    return $dateObj->format('F'); // March
}

}
?>