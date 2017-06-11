<?php

class DB_Decorator {

	protected $register;
	protected $db;

	public function __construct(Register $register) {
	  $this->register = $register;
      $this->resetDB();
    }

    function resetDB() {
	  $this->db = $this->register->getDB();
	}


}