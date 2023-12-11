package com.miracle.userservice.controller.sort;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public enum CoverLetterListSort implements Sortable {

    MODIFIED_AT_ASC {
        @Override
        public Sort toSort() {
            return Sort.by(Order.asc("modifiedAt"));
        }
    },

    MODIFIED_AT_DESC {
        @Override
        public Sort toSort() {
            return Sort.by(Order.desc("modifiedAt"));
        }
    }
}
