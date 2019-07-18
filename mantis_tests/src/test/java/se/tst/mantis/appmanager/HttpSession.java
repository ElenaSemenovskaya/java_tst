package se.tst.mantis.appmanager;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpSession {

    private CloseableHttpClient httpClient;
    private ApplicationManager app;

    public HttpSession(ApplicationManager app) {
        this.app = app;
        httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build(); //новая сессия (когда вызыывается new session)
    }

    public boolean login (String username, String password) throws IOException {  //собственно логин
        HttpPost post = new HttpPost(app.getProperty("web.baseUrl")+ "/login.php"); //создается будущий запрос типа post
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(); //формирует набор параметров
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("secure_session", "on"));
        params.add(new BasicNameValuePair("return", "index.php"));
        post.setEntity(new UrlEncodedFormEntity(params)); //параметры упаковываются и помещаются в заранее созданный запрос post.setEntity
        CloseableHttpResponse response = httpClient.execute(post); //отправка запроса
        String body = geTextFrom(response); //получение текста ответа
        return body.contains(String.format("<span class='user-info'>%s</span>", username)); //проверяется, действительно ли пользователь успешно вошел
    }

    private String geTextFrom(CloseableHttpResponse response) throws IOException{
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }

    public boolean isLoggedInAs (String username) throws IOException {  //какой пользователь сейчас вошел в систему
        HttpGet get = new HttpGet(app.getProperty("web.baseUrl")+ "/index.php"); //создается будущий запрос (типа Get)
        CloseableHttpResponse response = httpClient.execute(get); //отправка запроса
        String body = geTextFrom(response); //получение текста ответа
        return body.contains(String.format("<span class='user-info'>%s</span>", username));
    }

}
