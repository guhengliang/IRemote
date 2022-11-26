package com.guhl.remote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.guhl.remote.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ConsumerIrManagerApi consumerIrManagerApi;

    private RoundMenuView mRegionView;

    private EditText et_customer;
    private EditText et_key;
    private TextView tv_freqs;
    private int pro_index=0;
    private int pro_count=3;
    private String pro_name[] = {"NEC","KONKA","Samsung"};
    private String default_cusId[] = {"DF20","02","0E0E"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButton();

        consumerIrManagerApi = ConsumerIrManagerApi.getInstance();
        consumerIrManagerApi.init(this);

        mRegionView = (RoundMenuView) findViewById(R.id.my_roundMenuView);
        mRegionView.setListener(new RoundMenuView.RegionViewClickListener() {

            @Override
            public void clickTop() {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_up);
            }

            @Override
            public void clickRight() {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_right);
            }

            @Override
            public void clickLeft() {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_left);
            }

            @Override
            public void clickCenter() {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_center);
            }

            @Override
            public void clickBottom() {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_down);
            }
        });

    }

    private void initButton(){
        et_customer = (EditText)findViewById(R.id.et_customerId);
        et_key = (EditText)findViewById(R.id.et_KeyId);
        tv_freqs = (TextView)findViewById(R.id.freqs_text);

        Button btn_exit = (Button)findViewById(R.id.btn_exit);
        Button btn_epg = (Button)findViewById(R.id.btn_epg);
        Button btn_menu = (Button)findViewById(R.id.btn_menu);
        Button btn_source = (Button)findViewById(R.id.btn_source);

        Button btn_power = (Button)findViewById(R.id.btn_power);
        Button btn_home = (Button)findViewById(R.id.btn_home);
        Button btn_chrom = (Button)findViewById(R.id.btn_chrom);

        Button btn_ch_up = (Button)findViewById(R.id.btn_CH_up);
        Button btn_ch_down = (Button)findViewById(R.id.btn_CH_down);
        Button btn_vol_up = (Button)findViewById(R.id.btn_VOL_up);
        Button btn_vol_down = (Button)findViewById(R.id.btn_VOL_down);

        Button btn_submit = (Button)findViewById(R.id.btn_send);
        Button btn_submit2 = (Button)findViewById(R.id.btn_send2);
//        Button btn_receive = (Button)findViewById(R.id.btn_receive);
        Button btn_pro_switch = (Button)findViewById(R.id.btn_pro_switch);

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_exit);
            }
        });
        btn_epg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_epg);
            }
        });
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_menu);
            }
        });
        btn_source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_source);
            }
        });
        btn_power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_power);
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_home);
            }
        });
        btn_chrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_chrom);
            }
        });
        btn_ch_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_CH_up);
            }
        });
        btn_ch_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_CH_dowm);
            }
        });
        btn_vol_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_VOL_up);
            }
        });
        btn_vol_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_VOL_dowm);
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerId = et_customer.getText().toString();
                String keyCode = et_key.getText().toString();
                /*
                if (customerId.length()!=4 || !isHexNumber(customerId)){
                    tv_freqs.setText("客户码输入错误！");
                    return;
                }
                if (keyCode.length()!=2 || !isHexNumber(keyCode)){
                    tv_freqs.setText("遥控器码值输入错误！");
                    return;
                }
                */
                switch (pro_index) {
                    case 2://Samsung
                        //华为P20升级鸿蒙系统后此接口不能使用
                        //consumerIrManagerApi.transmit(38000, IRlevel_SAM(keyCode + customerId));//低位在前
                        tv_freqs.setText("已发送遥控码值：0x"+keyCode+"0x"+customerId);// 显示结果
                    case 1://KONKA
                        //华为P20升级鸿蒙系统后此接口不能使用
                        //consumerIrManagerApi.transmit(38000, IRlevel_KK(customerId + keyCode));//高位在前
                        tv_freqs.setText("已发送遥控码值：0x"+customerId+"0x"+keyCode);// 显示结果
                        break;
                    case 0://NEC
                    default:
                        keyCode = cuclationKeyCode(keyCode);
                        //华为P20升级鸿蒙系统后此接口不能使用
                        //consumerIrManagerApi.transmit(38000, IRlevel(keyCode + customerId));//低位在前
                        tv_freqs.setText("已发送遥控码值：0x"+keyCode+"0x"+customerId);// 显示结果
                }
            }
        });
        btn_submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (pro_index) {
                    case 2://Samsung
                        //consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_repeat_ss);
                    case 1://KONKA
                        //todo
                        break;
                    case 0://NEC
                    default:
                        //consumerIrManagerApi.transmit(38000, RemoteControlCode.pattern_repeat_nec);
                }
            }
        });
//        btn_receive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                StringBuilder b = new StringBuilder();
//                ConsumerIrManager.CarrierFrequencyRange[] freqs = consumerIrManagerApi.getCarrierFrequencies();
//                b.append("IR Carrier Frequencies:\n");// 红外载波频率
//                for (ConsumerIrManager.CarrierFrequencyRange range : freqs) {
//                    b.append(String.format("%d - %d\n", range.getMinFrequency(), range.getMaxFrequency()));
//                }
//                tv_freqs.setText(b.toString());// 显示结果
//            }
//        });
        btn_pro_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pro_index==pro_count-1){
                    pro_index = 0;
                }
                else{
                    pro_index++;
                }
                btn_pro_switch.setText(pro_name[pro_index]);
                et_customer.setText(default_cusId[pro_index]);
            }
        });

    }
    public boolean isHexNumber(String s){
        String regex="^[A-Fa-f0-9]+$";
        if(s.matches(regex)) {
            return true;
        }
        return false;
    }
    // 计算遥控器码值十六进制
    private String cuclationKeyCode(String code){
        code = code.toUpperCase();
        int code_10 = Integer.parseInt(code, 16);
        int code_FF = Integer.parseInt("FF", 16);
        String inverseCode = Integer.toHexString(code_FF-code_10).toUpperCase();
        return inverseCode+code;
    }
    // 根据十六进制遥控码值转换成遥控发射数组
    public static int[] IRlevel(String IRCODE) {
        String ma=IRCODE;
        char aj[]=ma.toCharArray();
        String ajer="";
        for (int i = 0; i < aj.length; i++) {
            ajer+=eraj(aj[i]+"");
        }
        System.out.println(ajer);
        List<Integer> list=new ArrayList<Integer>();
        list.add(9000);list.add(4500);
        for (int i =ajer.length()-1; i >=0; i--) {
            if (ajer.charAt(i)=='1') {
                list.add(560);list.add(1680);
            }else {
                list.add(560);list.add(560);
            }
        }
        list.add(560);list.add(20000);
        int pattern[]=new int[list.size()];
        for (int i = 0; i < pattern.length; i++) {
            pattern[i]=list.get(i);
        }
        System.out.println(list.toString());
        return pattern;
    }

    // 根据十六进制遥控码值转换成遥控发射数组
    public static int[] IRlevel_KK(String IRCODE) {
        String ma=IRCODE;
        char aj[]=ma.toCharArray();
        String ajer="";

        for (int i = 0; i < aj.length; i++) {
            ajer+=eraj(aj[i]+"");
        }

        System.out.println(ajer);
        List<Integer> list=new ArrayList<Integer>();
        list.add(3000);list.add(3000);
        for (int i =0; i <=ajer.length()-1; i++) {
            if (ajer.charAt(i)=='1') {
                list.add(500);list.add(2500);
            }else {
                list.add(500);list.add(1500);
            }
        }
        list.add(500);list.add(40000);
        int pattern[]=new int[list.size()];
        for (int i = 0; i < pattern.length; i++) {
            pattern[i]=list.get(i);
        }
        System.out.println(list.toString());
        return pattern;
    }

    // 根据十六进制遥控码值转换成遥控发射数组
    public static int[] IRlevel_SAM(String IRCODE) {
        String ma=IRCODE;
        char aj[]=ma.toCharArray();
        String ajer="";
        for (int i = 0; i < aj.length; i++) {
            ajer+=eraj(aj[i]+"");
        }
        System.out.println(ajer);
        List<Integer> list=new ArrayList<Integer>();
        list.add(4500);list.add(4500);
        for (int i =ajer.length()-1; i >=0; i--) {
            if (ajer.charAt(i)=='1') {
                list.add(560);list.add(1690);
            }else {
                list.add(560);list.add(560);
            }
        }
        list.add(560);list.add(20000);
        int pattern[]=new int[list.size()];
        for (int i = 0; i < pattern.length; i++) {
            pattern[i]=list.get(i);
        }
        System.out.println(list.toString());
        return pattern;
    }

    public static String eraj(String string) {
        string=Integer.toBinaryString(Integer.parseInt(string,16))+"";
        if (string.length()<4) {
            int sum =4-string.length();
            for (int i = 0; i <sum; i++) {
                string="0"+string;
            }
        }
        return string;
    }
}