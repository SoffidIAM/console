package com.soffid.iam.api;

import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class StatsSample {
	long min;
	long max;
	long sum;
	long average;
	long instances;
}
