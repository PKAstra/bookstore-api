package com.example.bookstoreapi;

import javax.persistence.GenerationType;

public @interface GeneratedValue {

    GenerationType strategy();

}
