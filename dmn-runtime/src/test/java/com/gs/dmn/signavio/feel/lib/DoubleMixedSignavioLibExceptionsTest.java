/*
 * Copyright 2016 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.gs.dmn.signavio.feel.lib;

import com.gs.dmn.feel.lib.DoubleMixedJavaTimeFEELLib;
import com.gs.dmn.feel.lib.StandardFEELLib;
import com.gs.dmn.feel.lib.stub.*;
import com.gs.dmn.feel.lib.type.bool.BooleanType;
import com.gs.dmn.feel.lib.type.context.ContextType;
import com.gs.dmn.feel.lib.type.function.FunctionType;
import com.gs.dmn.feel.lib.type.list.ListType;
import com.gs.dmn.feel.lib.type.numeric.NumericType;
import com.gs.dmn.feel.lib.type.range.RangeType;
import com.gs.dmn.feel.lib.type.string.StringType;
import com.gs.dmn.feel.lib.type.time.DateTimeType;
import com.gs.dmn.feel.lib.type.time.DateType;
import com.gs.dmn.feel.lib.type.time.DurationType;
import com.gs.dmn.feel.lib.type.time.TimeType;
import com.gs.dmn.signavio.feel.lib.stub.SignavioDateTimeLibStub;
import com.gs.dmn.signavio.feel.lib.stub.SignavioListLibStub;
import com.gs.dmn.signavio.feel.lib.stub.SignavioNumberLibStub;
import com.gs.dmn.signavio.feel.lib.stub.SignavioStringLibStub;
import com.gs.dmn.signavio.feel.lib.type.list.SignavioListLib;
import com.gs.dmn.signavio.feel.lib.type.numeric.SignavioNumberLib;
import com.gs.dmn.signavio.feel.lib.type.string.SignavioStringLib;
import com.gs.dmn.signavio.feel.lib.type.time.SignavioDateTimeLib;
import org.junit.Test;

import javax.xml.datatype.Duration;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertNull;

public class DoubleMixedSignavioLibExceptionsTest extends BaseSignavioLibExceptionsTest<Double, LocalDate, OffsetTime, ZonedDateTime, Duration> {
    @Override
    protected DoubleMixedJavaTimeSignavioLib getLib() {
        NumericType<Double> numericType = new NumericTypeStub<>();
        BooleanType booleanType = new BooleanTypeStub();
        StringType stringType = new StringTypeStub();
        DateType<LocalDate, Duration> dateType = new DateTypeStub<>();
        TimeType<OffsetTime, Duration> timeType = new TimeTypeStub<>();
        DateTimeType<ZonedDateTime, Duration> dateTimeType = new DateTimeTypeStub<>();
        DurationType<Duration, Double> durationType = new DurationTypeStub<>();
        ListType listType = new ListTypeStub();
        ContextType contextType = new ContextTypeStub();
        RangeType rangeType = new RangeTypeStub();
        FunctionType functionType = new FunctionTypeStub();
        StandardFEELLib<Double, LocalDate, OffsetTime, ZonedDateTime, Duration> feelLib = new DoubleMixedJavaTimeFEELLib();
        SignavioNumberLib<Double> numberLib = new SignavioNumberLibStub<>();
        SignavioStringLib stringLib = new SignavioStringLibStub();
        SignavioDateTimeLib<Double, LocalDate, OffsetTime, ZonedDateTime> dateTimeLib = new SignavioDateTimeLibStub<>();
        SignavioListLib listLib = new SignavioListLibStub();
        return new DoubleMixedJavaTimeSignavioLib(
                numericType, booleanType, stringType,
                dateType, timeType, dateTimeType, durationType,
                listType, contextType, rangeType, functionType,
                feelLib, numberLib, stringLib, dateTimeLib, listLib
        );
    }

    //
    // Date and time operations
    //
    @Override
    @Test
    public void testDay() {
        super.testDay();

        assertNull(getLib().day((ZonedDateTime) null));
    }

    @Override
    @Test
    public void testDayAdd() {
        super.testDayAdd();

        assertNull(getLib().dayAdd((ZonedDateTime) null, null));
    }

    @Override
    @Test
    public void testDayDiff() {
        super.testDayDiff();

        assertNull(getLib().dayDiff(null, (ZonedDateTime) null));
    }

    @Override
    @Test
    public void testHour() {
        super.testHour();

        assertNull(getLib().hour((ZonedDateTime) null));
    }

    @Override
    @Test
    public void testHourDiff() {
        super.testHourDiff();

        assertNull(getLib().hourDiff(null, (ZonedDateTime) null));
    }

    @Override
    @Test
    public void testMinute() {
        super.testMinute();

        assertNull(getLib().minute((ZonedDateTime) null));
    }

    @Override
    @Test
    public void testSecond() {
        super.testSecond();

        assertNull(getLib().second((ZonedDateTime) null));
    }

    @Override
    @Test
    public void testTimeOffset() {
        super.testTimeOffset();

        assertNull(getLib().timeOffset((ZonedDateTime) null));
    }

    @Override
    @Test
    public void testTimezone() {
        super.testTimezone();

        assertNull(getLib().timezone((ZonedDateTime) null));
    }

    @Override
    @Test
    public void testMinutesDiff() {
        super.testMinutesDiff();

        assertNull(getLib().minutesDiff(null, (ZonedDateTime) null));
    }

    @Override
    @Test
    public void testMonth() {
        super.testMonth();

        assertNull(getLib().month((ZonedDateTime) null));
    }

    @Override
    @Test
    public void testMonthAdd() {
        super.testMonthAdd();

        assertNull(getLib().monthAdd((ZonedDateTime) null, null));
    }

    @Override
    @Test
    public void testMonthDiff() {
        super.testMonthDiff();

        assertNull(getLib().monthDiff(null, (ZonedDateTime) null));
    }

    @Override
    @Test
    public void testWeekday() {
        super.testWeekday();

        assertNull(getLib().weekday((ZonedDateTime) null));
    }

    @Override
    @Test
    public void testYear() {
        super.testYear();

        assertNull(getLib().year((ZonedDateTime) null));
    }

    @Override
    @Test
    public void testYearAdd() {
        super.testYearAdd();

        assertNull(getLib().yearAdd((ZonedDateTime) null, null));
    }

    @Override
    @Test
    public void testYearDiff() {
        super.testYearDiff();

        assertNull(getLib().yearDiff((ZonedDateTime) null, null));
    }

    @Override
    @Test
    public void testDate() {
        super.testDate();

        assertNull(getLib().date((LocalDate) null));
    }

    @Override
    @Test
    public void testTime() {
        super.testTime();

        assertNull(getLib().time((OffsetTime) null));
    }
}