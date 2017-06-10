<?php
include_once('simple_html_dom.php');

function scraping_Wiki($url) {
    // create HTML DOM
    $html = file_get_html($url);

    $events = array();
    //*[@id="mw-content-text"]/div/ul[1]
    //*[@id="mw-content-text"]/div/ul[1]/li[1]
    $i = 0;
    foreach($html->find('ul') as $header) {
        foreach($header->find('li') as $line) {
            $events[$i] = $line->plaintext;
            $i++;
        }
    }
    /*for ($i = 6; $i < 26; $i++) {
        print_r($events[$i]);
        echo "<br>";
    }*/
    
    
    // clean up memory
    $html->clear();
    unset($html);
    return $events;
}


// -----------------------------------------------------------------------------
$today = $_GET["today"];
$json_object = json_decode($today);
$month = $json_object -> {"month"};
$day = $json_object -> {"day"};
$events = scraping_Wiki('https://en.wikipedia.org/wiki/'.$month.'_'.$day.'#Events');
echo json_encode($events);

?>