package com.miracle.userservice.controller.sort;

import org.springframework.data.domain.Sort;

public interface Sortable {

    Sort toSort();
}
