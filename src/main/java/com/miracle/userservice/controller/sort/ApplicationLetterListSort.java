package com.miracle.userservice.controller.sort;

import org.springframework.data.domain.Sort;

public enum ApplicationLetterListSort {

    NAME {
        @Override
        public Sort toSort() {
            return Sort.by(Sort.Order.asc("userName"));
        }
    },
    SUBMIT_DATE_ASC {
        @Override
        public Sort toSort() {
            return Sort.by(Sort.Order.asc("submitDate"));
        }
    },
    SUBMIT_DATE_DESC {
        @Override
        public Sort toSort() {
            return Sort.by(Sort.Order.desc("submitDate"));
        }
    };

    public abstract Sort toSort();
}

// 지원 날짜 기준 내림차순, 오름차순