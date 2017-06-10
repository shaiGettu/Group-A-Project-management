<?php

/**
 * Created by PhpStorm.
 * User: HP
 * Date: 07-May-17
 * Time: 2:44 PM
 */

header('Content-Type: text/plain; charset=utf-8;');

class Holidays{
    private $holidays = "";

    public function __construct($country, $year, $month) {
        $this->holidays="https://holidayapi.com/v1/holidays?key=4e451cc2-d557-4a52-b334-0ff8ed78b3a8&country=$country&year=$year&month=$month&form=json";
        echo $this->getHolidays();
    }

    private function getHolidays(){
        $result = file_get_contents($this->holidays);
        return $result;
    }
}
?>