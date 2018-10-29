package com.example.xiaoxiong.test;

import android.app.Dialog;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mnt.MntLib;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView responseText;
    private static int sTheme;

    //底部弹窗相关
    private View inflate;
    private TextView choosePhoto;
    private TextView takePhoto;
    private Button cancel;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (sTheme != 0) {
            //设置主题
            setTheme(sTheme);
        }
        setContentView(R.layout.activity_main);

        findViewById(R.id.bottom_dialog_show).setOnClickListener(this);
        Button dynamicTheme = (Button) findViewById(R.id.dynamic_theme);
        Button ratingbarTest = (Button) findViewById(R.id.ratingbar_test);
        Button fragmentTest = (Button) findViewById(R.id.fragment_test);
        Button broadcastTest = (Button) findViewById(R.id.broadcast_test);
        Button sendRequest = (Button) findViewById(R.id.send_request);
        Button listviewTest = (Button) findViewById(R.id.listviewtest);
        responseText = (TextView) findViewById(R.id.reponse_text);
        dynamicTheme.setOnClickListener(this);
        ratingbarTest.setOnClickListener(this);
        fragmentTest.setOnClickListener(this);
        broadcastTest.setOnClickListener(this);
        sendRequest.setOnClickListener(this);
        listviewTest.setOnClickListener(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //获取GoogleID
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String adid = AdvertisingIdClient.getGoogleAdId(getApplicationContext());
                    Log.e("MainActivity", "adid:  " + adid);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //获取AndroidID
        String ANDROID_ID = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
        Log.d("MainActivity", "Android ID: " + ANDROID_ID);


        //testReflectionField();

//        reflectTest();


//        testAOP();
//        testMnt();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom_dialog_show:
                show();
                break;
            case R.id.dynamic_theme:
                Intent intent0 = new Intent(this, DynamicThemeActivity.class);
                startActivity(intent0);
                break;
            case R.id.ratingbar_test:
                Intent intent = new Intent(this, RatingBarTestActivity.class);
                startActivity(intent);
                break;
            case R.id.send_request:
                //sendRequestWithHttpRulConnection();
                sendRequestWithOkHttp();
                break;
            case R.id.fragment_test:
                Intent intent1 = new Intent(this, FragmentTestActivity.class);
                startActivity(intent1);
                break;
            case R.id.broadcast_test:
                Intent intent2 = new Intent(this, BroadcastTestActivity.class);
                startActivity(intent2);
                break;
            case R.id.listviewtest:
                Intent intent3 = new Intent(this, ListViewTestActivity.class);
                startActivity(intent3);
                break;
            case R.id.choosePhoto:
                Toast.makeText(this,"choosePhoto",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                break;
            case R.id.takePhoto:
                Toast.makeText(this,"takePhoto",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                break;
            case R.id.btn_cancel:
                dialog.dismiss();
                break;
            default:
                break;
        }
    }

    private void sendRequestWithHttpRulConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder reponse = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        reponse.append(line);
                    }
                    showResponse(reponse.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder()
//                            .url("http://192.168.238.175/index2.xml")
//                            .build();
                    Request request = new Request.Builder()
                            .url("http://192.168.238.175/get_data.json")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    //showResponse(responseData);
                    //parseXMLWithPull(responseData);
                    parseJSONWithJSONObject(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(response);
            }
        });
    }

    private void parseXMLWithPull(String xmlData) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    //开始解析某个节点
                    case XmlPullParser.START_TAG: {
                        if ("id".equals(nodeName)) {
                            id = xmlPullParser.nextText();
                        } else if ("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        } else if ("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    }
                    //完成解析某个节点
                    case XmlPullParser.END_TAG: {
                        if ("app".equals(nodeName)) {
                            Log.d("MainActivity", "id is " + id);
                            Log.d("MainActivity", "name is " + name);
                            Log.d("MainActivity", "version is " + version);
                        }
                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.d("MainActivity", "id is " + id);
                Log.d("MainActivity", "name is " + name);
                Log.d("MainActivity", "version is " + version);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DebugTool
    private void testAOP() {

        Log.d("wangyuchao", "testAOP");
    }

    private void testMnt() {
        MntLib.init(this, "WJU7H7TM3MEYFKWYEUN03KNE");
    }


    public void testReflectionField() {
        try {
            //获取类
            Class c = Class.forName("java.lang.String");
            //获取类的所有属性
            Field[] fields = c.getDeclaredFields();
            StringBuffer sb = new StringBuffer();
            sb.append(Modifier.toString(c.getModifiers()) + " class " + c.getSimpleName() + "{\n");
            //遍历每一个属性
            for (Field field : fields) {
                sb.append("\t");//空格
                sb.append(Modifier.toString(field.getModifiers()) + " ");//获取属性的修饰符，如public
                sb.append(field.getType().getSimpleName() + " ");//属性类型的名字
                sb.append(field.getName() + ";\n");//属性名字+回车

            }
            sb.append("}\n");
            System.out.println("wangyuchao " + sb);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    public void testReflectionMethod() {
//        try {
//            //获取类
//            Class c = Class.forName("java.lang.String");
//            //获取所有的方法
//            Method[] ms = c.getDeclaredMethods();
//            //遍历输出所有的方法
//            for (Method method : ms) {
//                //获取方法所有参数
//                Parameter[] parameters = method.getParameters();
//                String params = "";
//                if (parameters.length > 0) {
//                    StringBuffer stringBuffer = new StringBuffer();
//                    for (Parameter parameter : parameters) {
//                        stringBuffer.append(parameter.getType().getSimpleName() + " " + parameter.getName() + ",");
//                    }
//                    //去掉最后一个逗号
//                    params = stringBuffer.substring(0, stringBuffer.length() - 1);
//                }
//                System.err.println(Modifier.toString(method.getModifiers())
//                        + " " + method.getReturnType().getSimpleName()
//                        + " " + method.getName()
//                        + " (" + params + "}");
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public void reflectTest() {

        try {
            //获取类
            Class c = Class.forName("com.example.xiaoxiong.test.TestClass");    //这里一定要用完整的包名
            //实例化一个TestClass对象
            TestClass tc = (TestClass) c.newInstance();
            //获取类的方法
            Method[] ms = c.getDeclaredMethods();
            for (Method method : ms) {
                if (method.isAnnotationPresent(TestClass.BindGet.class)) {
                    TestClass.BindGet bindGet = method.getAnnotation(TestClass.BindGet.class);
                    String param = bindGet.value();
                    method.invoke(tc, param);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.item1:
                    //将选中的主题资源id保存到静态变量中
                    sTheme = R.style.AppThemeLightBlue;
                    //重构Activity才能应用新的主题
                    recreate();
                    return true;
                case R.id.item2:
                    sTheme = R.style.AppThemeDeepBlue;
                    recreate();
                    return true;
                case R.id.item3:
                    sTheme = R.style.AppThemeGreen;
                    recreate();
                    return true;
                case R.id.item4:
                    sTheme = R.style.AppThemePink;
                    recreate();
                    return true;
            }
            return false;
        }

    };

    //底部弹窗相关
    public void show(){
        dialog = new Dialog(this,R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.popup_window_from_bottom,null);
        //初始化控件
        choosePhoto = (TextView)inflate.findViewById(R.id.choosePhoto);
        takePhoto = (TextView)inflate.findViewById(R.id.takePhoto);
        cancel = (Button)inflate.findViewById(R.id.btn_cancel);
        choosePhoto.setOnClickListener(this);
        takePhoto.setOnClickListener(this);
        cancel.setOnClickListener(this);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;//设置Dialog距离底部的距离
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }
}
