package net.thucydides.core.model;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class DataTableRow {
    private List<? extends Object> values;  // A list of strings or integers
    private TestResult result;

    public DataTableRow(List<? extends Object> values) {
        this(values, TestResult.UNDEFINED);
    }

    public DataTableRow(List<? extends Object> values, TestResult result) {
        this.values = new ArrayList<>(values);
        this.result = result;
    }

    public List<? extends Object> getValues() {
        return new ArrayList<>(values);
    }

    public List<String> getStringValues() {

        return Lists.transform(values, new Function<Object, String>() {
            @Override
            public String apply(Object o) {
                return o== null ? "" : o.toString();
            }
        });
    }

    public TestResult getResult() {
        return result;
    }

    public void setResult(TestResult result) {
        this.result = result;
    }

    public void updateResult(TestResult newResult) {
        if (newResult == TestResult.UNDEFINED) {
            setResult(newResult);
        } else {
            setResult(TestResultComparison.overallResultFor(this.result, newResult));
        }
    }

    @Override
    public String toString() {
        return "DataTableRow{" +
                "values=" + values +
                ", result=" + result +
                '}';
    }
}
