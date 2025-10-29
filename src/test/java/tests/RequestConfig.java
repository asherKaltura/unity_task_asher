package tests;

public class RequestConfig {

    private  RequestConfig(){



    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    private String port,url,body,method;


    public static class Builder{
        private  final RequestConfig config = new RequestConfig();

        public Builder body(String body){

            config.body = body;
            return this;
        }

        public Builder port(String port){

            config.port = port;
            return this;
        }
        public Builder method(String method){

            config.method = method;
            return this;
        }


        public Builder url(String url){

            config.url = url;
            return this;
        }

        public RequestConfig Build(){

            return config;
        }


    }







}
