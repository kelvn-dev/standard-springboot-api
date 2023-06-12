package com.kelvn.utils;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchCriteria {
	private String key;
	private String operator;
	private String value;
}
