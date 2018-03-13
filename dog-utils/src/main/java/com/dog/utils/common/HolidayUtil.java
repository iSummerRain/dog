package com.dog.utils.common;

import java.util.Calendar;

/**
 * 公历转农历工具类(1700-3100)
 * @author iceWater
 * @date 2017-04-12
 * @version 2.0
 */
public class HolidayUtil {

    private static String[] HOLIDAY_OUTER = new String[]{
            //元旦
            "0101",
            //劳动节
            "0501",
            //国庆节只
            "1001","1002","1003"
    };
    private static String[] HOLIDAY_INNER = new String[]{
            //春节
            "正月初一","正月初二","正月初三",
            //中秋
            "八月十五"
    };
    private static String[]  lunarInfo = new String[]{
            "19700206_1001011011010_00",
            "19710127_0100110111010_05",
            "19720215_0100101011010_00",
            "19730203_1010010011010_00",
            "19740123_1101010011010_04",
            "19750211_1101001001010_00",
            "19760131_1101010101010_08",
            "19770218_1011010101000_00",
            "19780207_1011011010100_00",
            "19790128_1001010110101_06",
            "19800216_1001010110110_00",
            "19810205_0100100110110_00",
            "19820125_1010100101110_04",
            "19830213_1010010010110_00",
            "19840202_1011001001110_10",
            "19850220_0110101001010_00",
            "19860209_0110110101000_00",
            "19870129_1010111101000_06",
            "19880217_1010101101100_00",
            "19890206_1001010101110_00",
            "19900127_0100101011110_05",
            "19910215_0100100101110_00",
            "19920204_0110010010110_00",
            "19930123_0111010010100_03",
            "19940210_1110101001010_00",
            "19950131_0110101101010_08",
            "19960219_0101101011000_00",
            "19970207_1010101101100_00",
            "19980128_1001011011010_05",
            "19990216_1001001011100_00",
            "20000205_1100100101100_00",
            "20010124_1101100101010_04",
            "20020212_1101010010100_00",
            "20030201_1101101001010_00",
            "20040122_0111010101010_02",
            "20050209_0101011010100_00",
            "20060129_1010101110110_07",
            "20070218_0010010111010_00",
            "20080207_1001001011010_00",
            "20090126_1100101010110_05",
            "20100214_1010100101010_00",
            "20110203_1011010010100_00",
            "20120123_1011101010100_04",
            "20130210_1010110101010_00",
            "20140131_0101010111010_09",
            "20150219_0100101110100_00",
            "20160208_1010010110110_00",
            "20170128_0101000101111_06",
            "20180216_0101001010110_00",
            "20190205_1010100100110_00",
            "20200125_0111100101010_04",
            "20210212_0110101010100_00",
            "20220201_1010110101010_00",
            "20230122_0101101101010_02",
            "20240210_0100101101100_00",
            "20250129_1010011011100_06",
            "20260217_1010010011100_00",
            "20270206_1101001001100_00",
            "20280126_1110101001100_05",
            "20290213_1101010100110_00",
            "20300203_0101101010100_00",
            "20310123_0111011010100_03",
            "20320211_1001011011010_00",
            "20330131_0100101011110_11",
            "20340219_0100101011010_00",
            "20350208_1010010011010_00",
            "20360128_1101000010111_06",
            "20370215_1101001001010_00",
            "20380204_1101010100100_00",
            "20390124_1101110101000_05",
            "20400212_1011010110100_00",
            "20410201_0101011011010_00",
            "20420122_0101010110110_02",
            "20430210_0100100110110_00",
            "20440130_1010010101110_07",
            "20450217_1010010010110_00",
            "20460206_1010101001010_00",
            "20470126_1011001001011_05",
            "20480214_0110110100100_00",
            "20490202_1010110110100_00",
            "20500123_0100101101101_03",
            "20510211_1001001101110_00",
            "20520201_0100100111110_08",
            "20530219_0100100101110_00",
            "20540208_0110010010110_00",
            "20550128_0110100010101_06",
            "20560215_1110101001010_00",
            "20570204_0110101010100_00",
            "20580124_1010011011001_04",
            "20590212_1010101011100_00",
            "20600202_1001001011100_00",
            "20610121_1101001011100_03",
            "20620209_1100100101100_00",
            "20630129_1101010101010_07",
            "20640217_1101010010100_00",
            "20650205_1101101001010_00",
            "20660126_0101110101010_05",
            "20670214_0101011010100_00",
            "20680203_1010011011010_00",
            "20690123_0101010111010_04",
            "20700211_0101001011010_00",
            "20710131_1010100110110_08",
            "20720219_1010100101010_00",
            "20730207_1011010010100_00",
            "20740127_1011011010100_06",
            "20750215_1010110101010_00",
            "20760205_0101010110100_00",
            "20770124_1010101110100_04",
            "20780212_1010010110110_00",
            "20790202_0101001010110_00",
            "20800122_1011001001110_03",
            "20810209_0110100100110_00",
            "20820129_0111001100110_07",
            "20830217_0110101010100_00",
            "20840206_1010110101010_00",
            "20850126_0100101101011_05",
            "20860214_0100101101100_00",
            "20870203_1010010101110_00",
            "20880124_0101010011100_04",
            "20890210_1101000101100_00",
            "20900130_1110100101100_08",
            "20910218_1101010100100_00",
            "20920207_1101101010100_00",
            "20930127_0110101010101_06",
            "20940215_0101011011010_00",
            "20950205_0100101011100_00",
            "20960125_1010100111010_04",
            "20970212_1010001011010_00",
            "20980201_1101000101010_00",
            "20990121_1111001001010_02"
    };
    /**
     * 将月份第十三位规定为闰月大小
     */
    private static int FIRST_YEAR = -1;
    private static int LAST_YEAR = -1;
    private static final String[] dataTopInit = init();

    /**
     * 该月份之前的天数
     * @param month
     * @return
     */
    private static int addDays(int month) {
        switch (month) {
            case 1:
                return 0;
            case 2:
                return 31;
            case 3:
                return 59;
            case 4:
                return 90;
            case 5:
                return 120;
            case 6:
                return 151;
            case 7:
                return 181;
            case 8:
                return 212;
            case 9:
                return 243;
            case 10:
                return 273;
            case 11:
                return 304;
            case 12:
                return 334;
            default:
                throw new RuntimeException("错误");
        }
    }

    /**
     * 判断是否为闰年
     * @param year
     * @return
     */
    private static boolean isLeapYear(int year) {
        if (year % 172800 == 0 || year % 400 == 0 && year % 3200 != 0 || year % 4 == 0 && year % 100 != 0)
            return true;
        return false;
    }

    /**
     * 一年中的第几天 1.1是第一天
     * @param year
     * @param month
     * @param day
     * @return
     */
    private static int getDays(int year, int month, int day) {
        int sum = addDays(month) + day;
        if (isLeapYear(year) && month > 2) {
            sum++;
        }
        return sum;
    }

    /**
     * 字符串获取一年中的第几天
     * @param date
     * @return
     */
    private static int getDays(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));
        int day = Integer.parseInt(date.substring(6, 8));
        return getDays(year, month, day);
    }

    /**
     * 将数字转化为汉字的数字,适应任何长度
     * @param year
     * @return
     */
    private static String formatYear(int year) {
        String table = "零一二三四五六七八九";
        StringBuilder result = new StringBuilder("");
        int year2 = year;
        while (year2 != 0) {
            int every = year2 % 10;
            result.append(table.substring(every, every + 1));
            year2 /= 10;
        }
        return result.reverse().toString();
    }

    /**
     * 月份转化为汉字,不含"月"字.
     * 1为正月,2为二月,13为闰正月,24为闰腊月
     * @param month
     * @return
     */
    private static String formatMonth(int month) {
        String table = "正二三四五六七八九十冬腊";
        if (month > 12) {
            return "闰" + table.substring(month - 13, month - 12);
        }
        return table.substring(month - 1, month);
    }

    /**
     * 天数转为农历汉字天数
     * 1为初一 30为三十
     * @param day
     * @return
     */
    private static String formatDay(int day) {
        String table = "十一二三四五六七八九";
        int day1 = day / 10;
        int day2 = day % 10;
        day1 -= day2 == 0 ? 1 : 0;
        String result = table.substring(day2, day2 + 1);
        if (day == 30) {
            return "三十";
        } else if (day == 20) {
            return "二十";
        } else {
            if (day1 == 0) {
                return "初" + result;
            } else if (day1 == 1) {
                return "十" + result;
            } else if (day1 == 2) {
                return "廿" + result;
            } else {
                throw new RuntimeException("不存在的农历日期");
            }
        }
    }

    private static void load(String[] dataInit, String data, int startYear) {
        String year = data.substring(0, 4);
        int numYear = Integer.parseInt(year);
        dataInit[numYear - startYear] = data;
    }

    private static String[] load(String[] data) {
        String[] dataTemp = new String[data.length - 1];
        for (int i = 1; i < data.length; i++) {
            dataTemp[i - 1] = addLastMonth(data[i], data[i - 1]);

        }
        return dataTemp;
    }

    /**
     * 17000219_  1010010010110_00
     * 17000219_101010010010110_00
     * 在年后面的下划线处插入mid
     * @param thisYear
     * @param lastYear
     * @return
     */
    private static String addLastMonth(String thisYear, String lastYear) {
        String last = lastYear.substring(19);//last=110_07
        String mid = getNovemberAndDecember(last);//mid=10
        StringBuilder sb = new StringBuilder(thisYear);
        String result = sb.insert(9, mid).toString();
        return result;
    }

    private static int[] cast(String start, String now, int[] bigOrLitter, int leap) {
        int numStart = getDays(start);//新年的累计天数
        int numNow = getDays(now);//当前日期的累计天数
        int dif = numNow - numStart;//当前日期相对天数,相对新年 新年为0天
        int[]bigOrLitterSort=resetSort(bigOrLitter,leap);
        int sum = 0 - bigOrLitterSort[0] - bigOrLitterSort[1] - 29 - 29;//去年11月1日的相对天数,为负数
        int i = 0;//月份
        while (dif >= sum) {
            sum += (bigOrLitterSort[i++] + 29);// 加上每月的农历天数
        }
        int year = Integer.parseInt(now.substring(0, 4));//获取年份
        int[] result = new int[3];// 数组分别存储年月日.
        result[0] = dif < 0 ? year - 1 : year;//在过年前 取去年,在过年后 年份取今年.
        result[1] = i - 2 <= 0 ? i + 10 : i - 2;//月份  去年的11月是第一个月
        if (dif >= 0) {//过年以后
            if (leap != 0) {
                if (result[1] == leap + 1) {//当前月就是闰月
                    result[1] += 11;//闰月加12,从0开始又减1
                } else if (result[1] > leap + 1) {//当前位于闰月之后
                    result[1]--;//减去闰掉的那个月
                }
            }
        } else {
            int numYear = year;
            int startYear = FIRST_YEAR;
            String[] dataInit = dataTopInit;
            String data = dataInit[numYear - startYear];
            String leapStr = data.substring(23, 25);
            int lastLeap = Integer.parseInt(leapStr);
            if (lastLeap != 0) {
                if (result[1] == lastLeap) {
                    if (lastLeap == 11) {
                        result[1] = 23;
                    } else if (lastLeap == 12) {
                        result[1] = 24;
                    } else {
                        throw new RuntimeException("-闰年错误,请联系作者修正-");
                    }
                } else {
                    if (lastLeap == 11 && result[1] == 12 || lastLeap == 12 && result[1] == 11) {
                        result[1] = 12;
                    } else {
                        // throw new RuntimeException("-闰年错误,请联系作者修正-");
                    }
                }
            }
        }
        result[2] = dif - sum + bigOrLitterSort[i - 1] + 29 + 1;//计算日期
        return result;
    }

    private static int[] resetSort(int[] bigOrLitter, int leap) {
        int len=bigOrLitter.length;//15
        int[] bigOrLitterSort = new int[len];
        if(leap == 0){//直接复制数组
            for (int i = 0; i < len; i++) {
                bigOrLitterSort[i] = bigOrLitter[i];
            }
        }else{//插入闰月大小
            for (int i = 0; i < len; i++) {
                int index = i - 2;
                if (index > leap) {
                    bigOrLitterSort[i] = bigOrLitter[i - 1];
                } else if (index == leap) {
                    bigOrLitterSort[i] = bigOrLitter[len - 1];// 14
                } else {
                    bigOrLitterSort[i] = bigOrLitter[i];
                }
            }
        }
        return bigOrLitterSort;
    }

    private static int[] cast(String now, String data) {
        String start = data.substring(0, 8);//春节年月日
        String bigOrLitterStr = data.substring(9, 24);//15个月的大小月 包含去年的两个月与今年的闰月大小
        String leapStr = data.substring(25, 27);//闰月闰的月份两位数
        int[] bigOrLitter = new int[15];//将15个月的大小转为数组
        for (int i = 0; i < bigOrLitter.length; i++) {
            bigOrLitter[i] = Integer.parseInt(bigOrLitterStr.substring(i, i + 1));
        }
        int leap = Integer.parseInt(leapStr);//闰月数转为数字,闰的月份
        return cast(start, now, bigOrLitter, leap);
    }

    /**
     * 取前两位(上一年的十一月和十二月)
     * 确保每一行都包含前一年的信息。（前一年的11月和12月份）
     * last="110_07"
     * mid="10"
     * @param last
     * @return
     */
    private static String getNovemberAndDecember(String last) {
        return last.substring(0, 2);
    }

    private static int[] cast(String now, String[] dataInit, int startYear) {
        String year = now.substring(0, 4);
        int numYear = Integer.parseInt(year);
        String data = dataInit[numYear - startYear];
        String dataLast = dataInit[numYear - startYear - 1];
        if (dataLast.endsWith("_11")) {
            String newStr = subLeapNovember(dataLast);
            data = replaceLastYearMonth(data, newStr);
        } else if (dataLast.endsWith("_12")) {
            String newStr = subLeapDecember(dataLast);
            data = replaceLastYearMonth(data, newStr);
        }
        return cast(now, data);
    }

    /**
     * 替换去年12月与11月部分，共15位月份
     * @param str
     * @param newStr
     * @return
     */
    private static String replaceLastYearMonth(String str, String newStr) {
        return str.substring(0, 9) + newStr+str.substring(11);
    }

    /**
     * 去年闰十一月
     * @param str
     * @return
     */
    private static String subLeapNovember(String str) {
        return str.substring(23,24)+str.substring(22,23);
    }

    /**
     * 去年闰十二月
     * @param str
     * @return
     */
    private static String subLeapDecember(String str) {
        return str.substring(22,24);
    }

    private static int[] judge(String date) {
        int year = -1;
        int month = -1;
        int day = -1;
        if (date.matches("\\d{8}")) {
            year = Integer.parseInt(date.substring(0, 4));
            month = Integer.parseInt(date.substring(4, 6));
            day = Integer.parseInt(date.substring(6, 8));
        } else if (date.matches("\\d+-\\d{1,2}-\\d{1,2}")) {
            String[] dateArray = date.split("-");
            year = Integer.parseInt(dateArray[0]);
            month = Integer.parseInt(dateArray[1]);
            day = Integer.parseInt(dateArray[2]);
        } else if (date.matches("\\d+\\.\\d{1,2}\\.\\d{1,2}")) {
            String[] dateArray = date.split("\\.");
            year = Integer.parseInt(dateArray[0]);
            month = Integer.parseInt(dateArray[1]);
            day = Integer.parseInt(dateArray[2]);
        } else if (date.matches("\\d+/\\d{1,2}/\\d{1,2}")) {
            String[] dateArray = date.split("/");
            year = Integer.parseInt(dateArray[0]);
            month = Integer.parseInt(dateArray[1]);
            day = Integer.parseInt(dateArray[2]);
        } else if (date.matches("\\d+年\\d{1,2}月\\d{1,2}日")) {
            String[] dateArray = date.split("年|月|日");
            year = Integer.parseInt(dateArray[0]);
            month = Integer.parseInt(dateArray[1]);
            day = Integer.parseInt(dateArray[2]);
        } else {
            return null;
        }
        int[] result = new int[3];
        result[0] = year;
        result[1] = month;
        result[2] = day;
        return result;
    }

    private static boolean judge(int[] date) {
        int year = date[0];
        int month = date[1];
        int day = date[2];
        if (month > 12 || month < 1) {
            return false;
        } else if (day > 31 || day < 1) {
            return false;
        } else if (day == 31 && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11)) {
            return false;
        } else if (month == 2) {
            if (day > 29) {
                return false;
            } else if (!isLeapYear(year) && day > 28) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    /**
     * int数组转为字符串
     * @param date
     * @return
     */
    private static String cast(int[] date) {
        int year = date[0];
        int month = date[1];
        int day = date[2];
        String yearStr = addZero(year, 4);
        String monthStr = addZero(month, 2);
        String dayStr = addZero(day, 2);
        String result = yearStr + monthStr + dayStr;
        return result;
    }


    private static String addZero(int num, int size) {
        int len = (num + "").length();
        if (len < size) {
            char[] chs = new char[size - len];
            for (int i = 0; i < chs.length; i++) {
                chs[i] = '0';
            }
            return new String(chs) + num;
        } else {
            return num + "";
        }
    }

    /**
     * 将 公历日期字符串 转为农历数组， 公历字符串 格式 为8个数字，例如20120909 数组下标为0的是年，为1的是月，为2的是日。
     *
     * @param now
     *            公历日期字符串
     * @return 农历日期数组
     */
    private static int[] cast2Array(String now) {
        String[] dataTop = dataTopInit;
        String year = dataTop[1].substring(0, 4);
        int startYear = Integer.parseInt(year);
        String[] data = new String[dataTop.length];
        for (int i = 0; i < dataTop.length; i++) {
            load(data, dataTop[i], startYear - 1);
        }
        String[] data2 = null;
        data2 = load(data);
        return cast(now, data2, startYear);
    }

    /**
     * 将公历字符串转为农历字符串 公历字符串 格式 为8个数字，例如20120909
     *
     * @param date
     *            公历日期字符串
     * @return 农历日期字符串
     */
    public static String cast(String date) {
        StringBuilder sb = new StringBuilder("");
        int[] result = null;
        result = cast2Array(date);
        sb.append(formatYear(result[0]));
        sb.append("年");
        sb.append(formatMonth(result[1]));
        sb.append("月");
        sb.append(formatDay(result[2]));
        return sb.toString();
    }
    /**
     * 公历日期转农历日期,公历日期合法性经过检查. 推荐的调用方法
     *
     * @param date 公历日期
     * @return 农历日期
     */
    public static String getDate(String date) {
        int[] dateArray = judge(date);
        if (dateArray == null) {
            throw new RuntimeException("-输入的日期不合法-");
        } else if (judge(dateArray)) {
            int year = dateArray[0];
            if (year < FIRST_YEAR || year > LAST_YEAR) {
                throw new RuntimeException("-输入的日期年份超出范围,年份必须在" + FIRST_YEAR + "与" + LAST_YEAR + "之间-");
            } else {
                String dateStr = cast(dateArray);
                return cast(dateStr);
            }
        } else {
            throw new RuntimeException("-输入的日期不合法-");
        }
    }


    private static String[] init() {
        String[] dataTop = null;
        dataTop = lunarInfo;
        String year = dataTop[1].substring(0, 4);
        String lastYearStr = dataTop[dataTop.length - 1].substring(0, 4);
        int startYear = Integer.parseInt(year);
        int lastYear = Integer.parseInt(lastYearStr);
        FIRST_YEAR = startYear;
        LAST_YEAR = lastYear;
        return dataTop;
    }

    /**
     * 判断一个日期是否节假日
     @author jw
     @param dateNow 待处理日期 格式:yyyyMMdd
     */
    public static boolean judgeHoliday(String dateNow){ int yearNow = Calendar.getInstance().get(Calendar.YEAR);
        int yearLenOutter = 4;
        //判断当前日期是否阳历节日
        for(String date:HOLIDAY_OUTER)
        {
            String dateFullHoliday = date;
            //年份不进行比对
            if(dateNow.substring(yearLenOutter).equals(dateFullHoliday))
            {
                return true;
            }
        }
        //不是公历节假日则判断是否农历节假日
        String innerDayNow = HolidayUtil.getDate(dateNow);
        //判断当前日期是否农历节日
        int yearLenInner = 5;

        for(String date:HOLIDAY_INNER)
        {
            String dateFullHoliday = date;
            //剔除前面的年
            if(innerDayNow.substring(yearLenInner).equals(dateFullHoliday))
            {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args){
        System.out.println(judgeHoliday("20170101"));//true
        System.out.println(judgeHoliday("20170102"));//false
        System.out.println(judgeHoliday("20170127"));//false
        System.out.println(judgeHoliday("20170128"));//true
        System.out.println(judgeHoliday("20170129"));//true
        System.out.println(judgeHoliday("20170130"));//true
        System.out.println(judgeHoliday("20170131"));//false
        System.out.println(judgeHoliday("20170501"));//true
        System.out.println(judgeHoliday("20170502"));//false
        System.out.println(judgeHoliday("20170503"));//false
        System.out.println(judgeHoliday("20170530"));//true
        System.out.println(judgeHoliday("20170529"));//false
        System.out.println(judgeHoliday("20171004"));//true
        System.out.println(judgeHoliday("20171003"));//true
        System.out.println(judgeHoliday("20171005"));//false
    }
}
