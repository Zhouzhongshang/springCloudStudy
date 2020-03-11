package com.zhou.servicefeign.pojo;

import com.github.liaochong.myexcel.core.annotation.ExcelColumn;
import lombok.Data;

/**
 * @program: sc-f-chapter1
 * @description:
 * @author: zzs
 * @create: 2020-03-06 15:50
 **/
@Data
public class ArtCrowdVo {
        // Index represents column index, starting from 0
        // Annotation free import is supported, i.e. it does not need to specify the column corresponding to the field @Excelcolumn, and will be imported in the default order of all fields
        // Can be read according to the specified title
        /**
         * 国家英文
         */
        @ExcelColumn(index = 0,title = "数据名称")
        public String dataName;

        /**
         * 国家中文
         */
        @ExcelColumn(index = 1,title = "备注")
        public String ext1;

        /**
         * 国家所属区域
         */
        @ExcelColumn(index = 2,title = "所属区域")
        public String areas;


        /**
         * 字典码(自己设置)
         */
        public String dataValue;

        /**
         * 父id 父码
         */
        public String parentid;

        /**
         * dictName[海外国家，海外区域等等]
         */
        public String dictName;

        public String getDataName() {
                return dataName;
        }

        public void setDataName(String dataName) {
                this.dataName = dataName;
        }

        public String getExt1() {
                return ext1;
        }

        public void setExt1(String ext1) {
                this.ext1 = ext1;
        }

        public String getAreas() {
                return areas;
        }

        public void setAreas(String areas) {
                this.areas = areas;
        }

        public String getDataValue() {
                return dataValue;
        }

        public void setDataValue(String dataValue) {
                this.dataValue = dataValue;
        }

        public String getParentid() {
                return parentid;
        }

        public void setParentid(String parentid) {
                this.parentid = parentid;
        }

        public String getDictName() {
                return dictName;
        }

        public void setDictName(String dictName) {
                this.dictName = dictName;
        }

        public ArtCrowdVo() {
        }

        public ArtCrowdVo(String dataName, String ext1, String areas, String dataValue, String parentid, String dictName) {
                this.dataName = dataName;
                this.ext1 = ext1;
                this.areas = areas;
                this.dataValue = dataValue;
                this.parentid = parentid;
                this.dictName = dictName;
        }

        @Override
        public String toString() {
                return "ArtCrowdVo{" +
                        "dataName='" + dataName + '\'' +
                        ", ext1='" + ext1 + '\'' +
                        ", areas='" + areas + '\'' +
                        ", dataValue='" + dataValue + '\'' +
                        ", parentid='" + parentid + '\'' +
                        ", dictName='" + dictName + '\'' +
                        '}';
        }
}


