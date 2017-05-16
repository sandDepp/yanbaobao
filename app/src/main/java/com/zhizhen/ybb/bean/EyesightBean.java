package com.zhizhen.ybb.bean;

import java.util.List;

/**
 * Created by sandlovechao on 2017/5/15.
 */

public class EyesightBean {

    private String status;
    private String statusInfo;
    private List<EyesightInfo> data;

    public List<EyesightInfo> getData() {
        return data;
    }

    public void setData(List<EyesightInfo> data) {
        this.data = data;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class EyesightInfo {
        private String left_eye_degree;
        private String right_eye_degree;
        private String left_eye_astigmatism;
        private String right_eye_astigmatism;
        private String pupillary_distance;

        public String getLeft_eye_degree() {
            return left_eye_degree;
        }

        public void setLeft_eye_degree(String left_eye_degree) {
            this.left_eye_degree = left_eye_degree;
        }

        public String getRight_eye_degree() {
            return right_eye_degree;
        }

        public void setRight_eye_degree(String right_eye_degree) {
            this.right_eye_degree = right_eye_degree;
        }

        public String getLeft_eye_astigmatism() {
            return left_eye_astigmatism;
        }

        public void setLeft_eye_astigmatism(String left_eye_astigmatism) {
            this.left_eye_astigmatism = left_eye_astigmatism;
        }

        public String getRight_eye_astigmatism() {
            return right_eye_astigmatism;
        }

        public void setRight_eye_astigmatism(String right_eye_astigmatism) {
            this.right_eye_astigmatism = right_eye_astigmatism;
        }

        public String getPupillary_distance() {
            return pupillary_distance;
        }

        public void setPupillary_distance(String pupillary_distance) {
            this.pupillary_distance = pupillary_distance;
        }

        @Override
        public String toString() {
            return "EyesightBean{" +
                    "left_eye_degree='" + left_eye_degree + '\'' +
                    ", right_eye_degree='" + right_eye_degree + '\'' +
                    ", left_eye_astigmatism='" + left_eye_astigmatism + '\'' +
                    ", right_eye_astigmatism='" + right_eye_astigmatism + '\'' +
                    ", pupillary_distance='" + pupillary_distance + '\'' +
                    '}';
        }
    }
}
