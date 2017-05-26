package com.shall.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mohamed S. El-Shall on 4/7/2017.
 */

public class StudentAssignment implements Serializable {


    /**
     * response : success
     * data : [{"research_id":"1","id":"11","name":"Research 1","start_date":"2016-04-14","recive_date":"2016-04-29","dept_id":"1","course_id":"10","aims":"Research Paper","files":"Research Project.doc","direct":"all","tutor_id":"26","course_name":{"name":"Project 2"},"tutor_name":{"name":"DR.basil"}}]
     */

    @SerializedName("response")
    private String response;
    @SerializedName("data")
    private List<Assignment> data;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<Assignment> getData() {
        return data;
    }

    public void setData(List<Assignment> data) {
        this.data = data;
    }

    public static class Assignment {
        /**
         * research_id : 1
         * id : 11
         * name : Research 1
         * start_date : 2016-04-14
         * recive_date : 2016-04-29
         * dept_id : 1
         * course_id : 10
         * aims : Research Paper
         * files : Research Project.doc
         * direct : all
         * tutor_id : 26
         * course_name : {"name":"Project 2"}
         * tutor_name : {"name":"DR.basil"}
         */
        @SerializedName("research_id")
        private String research_id;
        @SerializedName("id")
        private String id;
        @SerializedName("name")
        private String name;
        @SerializedName("start_date")
        private String start_date;
        @SerializedName("recive_date")
        private String recive_date;
        @SerializedName("dept_id")
        private String dept_id;
        @SerializedName("course_id")
        private String course_id;
        @SerializedName("aims")
        private String aims;
        @SerializedName("files")
        private String files;
        @SerializedName("direct")
        private String direct;
        @SerializedName("tutor_id")
        private String tutor_id;
        @SerializedName("course_name")
        private CourseNameBean course_name;
        @SerializedName("tutor_name")
        private TutorNameBean tutor_name;

        public String getResearch_id() {
            return research_id;
        }

        public void setResearch_id(String research_id) {
            this.research_id = research_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getRecive_date() {
            return recive_date;
        }

        public void setRecive_date(String recive_date) {
            this.recive_date = recive_date;
        }

        public String getDept_id() {
            return dept_id;
        }

        public void setDept_id(String dept_id) {
            this.dept_id = dept_id;
        }

        public String getCourse_id() {
            return course_id;
        }

        public void setCourse_id(String course_id) {
            this.course_id = course_id;
        }

        public String getAims() {
            return aims;
        }

        public void setAims(String aims) {
            this.aims = aims;
        }

        public String getFiles() {
            return files;
        }

        public void setFiles(String files) {
            this.files = files;
        }

        public String getDirect() {
            return direct;
        }

        public void setDirect(String direct) {
            this.direct = direct;
        }

        public String getTutor_id() {
            return tutor_id;
        }

        public void setTutor_id(String tutor_id) {
            this.tutor_id = tutor_id;
        }

        public CourseNameBean getCourse_name() {
            return course_name;
        }

        public void setCourse_name(CourseNameBean course_name) {
            this.course_name = course_name;
        }

        public TutorNameBean getTutor_name() {
            return tutor_name;
        }

        public void setTutor_name(TutorNameBean tutor_name) {
            this.tutor_name = tutor_name;
        }

        public static class CourseNameBean {
            /**
             * name : Project 2
             */
            @SerializedName("name")
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class TutorNameBean {
            /**
             * name : DR.basil
             */
            @SerializedName("name")
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
