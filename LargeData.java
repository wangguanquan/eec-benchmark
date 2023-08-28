
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import org.ttzero.excel.annotation.ExcelColumn;

import java.util.Date;


public class LargeData {
    @ExcelProperty
    @ExcelColumn
    private int nv;
    @ExcelProperty
    @ExcelColumn
    private long lv;
    @ExcelProperty
    @ExcelColumn
    private double dv;
    @ExcelProperty
    @ExcelColumn(format = "yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat("yyyy-MM-dd hh:mm:ss")
    private Date av;
    @ExcelProperty
    @ExcelColumn
    private String str1;
    @ExcelProperty
    @ExcelColumn
    private String str2;
    @ExcelProperty
    @ExcelColumn
    private String str3;
    @ExcelProperty
    @ExcelColumn
    private String str4;
    @ExcelProperty
    @ExcelColumn
    private String str5;
    @ExcelProperty
    @ExcelColumn
    private String str6;
    @ExcelProperty
    @ExcelColumn
    private String str7;
    @ExcelProperty
    @ExcelColumn
    private String str8;
    @ExcelProperty
    @ExcelColumn
    private String str9;
    @ExcelProperty
    @ExcelColumn
    private String str10;
    @ExcelProperty
    @ExcelColumn
    private String str11;
    @ExcelProperty
    @ExcelColumn
    private String str12;
    @ExcelProperty
    @ExcelColumn
    private String str13;
    @ExcelProperty
    @ExcelColumn
    private String str14;
    @ExcelProperty
    @ExcelColumn
    private String str15;

    public int getNv() {
        return nv;
    }

    public void setNv(int nv) {
        this.nv = nv;
    }

    public long getLv() {
        return lv;
    }

    public void setLv(long lv) {
        this.lv = lv;
    }

    public double getDv() {
        return dv;
    }

    public void setDv(double dv) {
        this.dv = dv;
    }

    public Date getAv() {
        return av;
    }

    public void setAv(Date av) {
        this.av = av;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public String getStr3() {
        return str3;
    }

    public void setStr3(String str3) {
        this.str3 = str3;
    }

    public String getStr4() {
        return str4;
    }

    public void setStr4(String str4) {
        this.str4 = str4;
    }

    public String getStr5() {
        return str5;
    }

    public void setStr5(String str5) {
        this.str5 = str5;
    }

    public String getStr6() {
        return str6;
    }

    public void setStr6(String str6) {
        this.str6 = str6;
    }

    public String getStr7() {
        return str7;
    }

    public void setStr7(String str7) {
        this.str7 = str7;
    }

    public String getStr8() {
        return str8;
    }

    public void setStr8(String str8) {
        this.str8 = str8;
    }

    public String getStr9() {
        return str9;
    }

    public void setStr9(String str9) {
        this.str9 = str9;
    }

    public String getStr10() {
        return str10;
    }

    public void setStr10(String str10) {
        this.str10 = str10;
    }

    public String getStr11() {
        return str11;
    }

    public void setStr11(String str11) {
        this.str11 = str11;
    }

    public String getStr12() {
        return str12;
    }

    public void setStr12(String str12) {
        this.str12 = str12;
    }

    public String getStr13() {
        return str13;
    }

    public void setStr13(String str13) {
        this.str13 = str13;
    }

    public String getStr14() {
        return str14;
    }

    public void setStr14(String str14) {
        this.str14 = str14;
    }

    public String getStr15() {
        return str15;
    }

    public void setStr15(String str15) {
        this.str15 = str15;
    }

    @Override
    public String toString() {
        return nv + " | " + lv + " | " + dv + " | " + av + " | " + str1 + " | " + str2 + " | " + str3 + " | " + str4 + " | " + str5 + " | " + str6 + " | " + str7 + " | " + str8 + " | " + str9 + " | " + str10 + " | " + str11 + " | " + str12 + " | " + str13 + " | " + str14 + " | " + str15;
    }
}
