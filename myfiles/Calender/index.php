<?php
/**
 * Created by PhpStorm.
 * User: HP
 * Date: 25-May-17
 * Time: 1:26 PM
 */
require_once "Holidays.php";

    $holiday = new Holidays($_POST['country'], $_POST['year'], $_POST['month']);