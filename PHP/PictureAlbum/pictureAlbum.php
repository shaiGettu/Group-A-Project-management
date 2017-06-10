<?php
/**
 * Created by PhpStorm.
 * User: Shai Gettu
 * Date: 27-May-17
 * Time: 6:51 PM
 */
require_once "Picture.php";
$pictures = new Picture();
$method = $_POST['method'];
if($method=="s"){
    echo json_encode($pictures->storePicture($_POST['user'],$_FILES['picture']));
}
else if($method=="g")
    echo json_encode($pictures->getPicturesByUser($_POST['user']));

/*$target_path  = "./Upload/";
$target_path = $target_path . basename( $_FILES['uploadedfile']['name']);
if(move_uploaded_file($_FILES['uploadedfile']['tmp_name'], $target_path)) {
    echo "The file ".  basename( $_FILES['uploadedfile']['name']).
        " has been uploaded";
} else{
    echo "There was an error uploading the file, please try again!";
}*/