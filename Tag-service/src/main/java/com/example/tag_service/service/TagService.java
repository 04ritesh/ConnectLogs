package com.example.tag_service.service;

import java.util.List;

public interface TagService {

    List<Long> createTags(List<String> names);

}
