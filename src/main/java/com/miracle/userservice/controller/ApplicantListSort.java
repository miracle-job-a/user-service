package com.miracle.userservice.controller;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public enum ApplicantListSort {

    NAME {
        @Override
        public Sort toSort() {
            return Sort.by(Order.asc("userName"));
        }
    },
    SUBMIT_DATE_ASC {
        @Override
        public Sort toSort() {
            return Sort.by(Order.asc("submitDate"));
        }
    },
    SUBMIT_DATE_DESC {
        @Override
        public Sort toSort() {
            return Sort.by(Order.desc("submitDate"));
        }
    };

    public abstract Sort toSort();
}
