#定位+移动选址

- ####百学须先立志---学前须知：

	我们经常在各大主流APP上要求被写上地址，如百度外卖、爱鲜蜂收货地址等等；其中他们大多数是可以让我们在地图上移动选址。就如下面这段GIF演示的一样：

	![最终演示](http://img.blog.csdn.net/20151012131942637)

- ####尽信书，不如无书---能学到什么？

	1、地图状态MapStatus类及监听setOnMapStatusChangeListener
	2、定位LocationClient类
	3、反地理编码GeoCoder类

- ####工欲善其事必先利其器---申请Key

	百度地图访问应用（AK）申请地址：http://lbsyun.baidu.com/apiconsole/key

	如果你是第一次申请的话可以参看我的另一篇教程：[Android中级篇之百度地图SDK v3.5.0-申请密钥详解[AndroidStudio下获取SHA1] ](http://blog.csdn.net/y1scp/article/details/48000761)

- ####兵马未动，粮草先行---导入百度地图jar包

	1、 进入[百度地图API-首页](http://developer.baidu.com/map/index.php?title=%E9%A6%96%E9%A1%B5)
	
	![百度地图API-首页](http://img.blog.csdn.net/20150928161244468)
	
	2、鼠标移动到 **开发** 标签页上，选择 **Android地图SDK**
	
	![Android地图SDK](http://img.blog.csdn.net/20150928161540537)

	3、选择 **相关下载**

	![相关下载](http://img.blog.csdn.net/20150928162954149)

	4、选择 **全部下载** (此时这里会跳转到新的下载页面)

	![全部下载](http://img.blog.csdn.net/20150928163204429)

	5、选择 **相对应的开发包(本教程大家选择和下图标注的一样即可)**

	![开发包](http://img.blog.csdn.net/20150928163415187)

	6、导入jar包到工程

	如果你是第一次导入的话可以参看我另一篇教程：[Android中级篇之百度地图SDK v3.5.0-配置环境及发布[图解AndroidStudio下配置.so文件]](http://blog.csdn.net/y1scp/article/details/48023947) 这里呢就不再赘述了。

	![导入jar包到工程](http://img.blog.csdn.net/20150928163752359)

- ####权限及服务---AndroidManifest

	```
	<?xml version="1.0" encoding="utf-8"?>
	<manifest xmlns:android="http://schemas.android.com/apk/res/android"
	    package="com.scp">
	
	    <!-- SDK2.1新增获取用户位置信息 -->
	    <uses-permission android:name="android.permission.ACCESS_LOCATION_EXTRA_COMMANDS" />
	    <uses-permission android:name="com.android.launcher.permission.READ_SETTINGS" />
	    <uses-permission android:name="android.permission.WAKE_LOCK"></uses-permission>
	    <!-- SDK1.5需要android.permission.GET_TASKS权限判断本程序是否为当前运行的应用? -->
	    <uses-permission android:name="android.permission.GET_TASKS" />
	    <uses-permission android:name="android.permission.WRITE_SETTINGS" />
	    <!-- 这个权限用于进行网络定位-->
	    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"></uses-permission>
	    <!-- 这个权限用于访问GPS定位-->
	    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"></uses-permission>
	    <!-- 用于访问wifi网络信息，wifi信息会用于进行网络定位-->
	    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
	    <!-- 获取运营商信息，用于支持提供运营商信息相关的接口-->
	    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
	    <!-- 这个权限用于获取wifi的获取权限，wifi信息会用来进行网络定位-->
	    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"></uses-permission>
	    <!-- 用于读取手机当前的状态-->
	    <uses-permission android:name="android.permission.READ_PHONE_STATE"></uses-permission>
	    <!-- 写入扩展存储，向扩展卡写入数据，用于写入离线定位数据-->
	    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
	    <!-- 访问网络，网络定位需要上网-->
	    <uses-permission android:name="android.permission.INTERNET" />
	    <!-- SD卡读取权限，用户写入离线定位数据-->
	    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"></uses-permission>
	    <!--允许应用读取低级别的系统日志文件 -->
	    <uses-permission android:name="android.permission.READ_LOGS"></uses-permission>
	    <!-- 定位所需的权限 -->
	    <uses-permission android:name="android.permission.VIBRATE" />
	
	    <uses-permission android:name="com.android.launcher.permission.READ_SETTINGS" />
	
	    <!--对于很高的分辨率，除采用相应的图片外，还需要加上如下配置，来更好的适配屏幕 -->
	    <supports-screens
	        android:anyDensity="true"
	        android:largeScreens="true"
	        android:normalScreens="true"
	        android:smallScreens="true" />
	
	    <application
	        android:allowBackup="true"
	        android:icon="@mipmap/ic_launcher"
	        android:label="@string/app_name"
	        android:theme="@style/AppTheme">
	        <meta-data
	            android:name="com.baidu.lbsapi.API_KEY"
	            android:value="你申请的百度地图KEY" />
	        <activity
	            android:name=".MainActivity"
	            android:label="@string/app_name">
	            <intent-filter>
	                <action android:name="android.intent.action.MAIN" />
	
	                <category android:name="android.intent.category.LAUNCHER" />
	            </intent-filter>
	        </activity>
	    </application>
	
	    <service
	        android:name="com.baidu.location.f"
	        android:enabled="true"
	        android:process=":remote">
	        <intent-filter>
	            <action android:name="com.baidu.location.service_v2.2"></action>
	        </intent-filter>
	    </service>
	
	</manifest>
	
	```

	![AndroidManifest](http://img.blog.csdn.net/20150928165951364)

- ####配置.so文件及其他---build.gradle

	```
	apply plugin: 'com.android.application'
	
	android {
	    compileSdkVersion 23
	    buildToolsVersion "23.0.0"
	
	    defaultConfig {
	        applicationId "com.scp"
	        minSdkVersion 14
	        targetSdkVersion 23
	        versionCode 1
	        versionName "1.0"
	    }
	    buildTypes {
	        release {
	            //代码混淆
	            minifyEnabled false
	            //zip优化
	            zipAlignEnabled true
	            //移除无用的resource文件
	            shrinkResources true
	            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.txt'
	        }
	    }
	    sourceSets {
	        main {
	            jniLibs.srcDirs = ['libs']
	        }
	    }
	}
	
	dependencies {
	    compile fileTree(include: ['*.jar'], dir: 'libs')
	    compile 'com.android.support:appcompat-v7:23.0.0'
	    compile files('libs/BaiduLBS_Android.jar')
	}
	
	```

	![build.gradle](http://img.blog.csdn.net/20150928170552125)

- ####主布局---activity_main.xml

	首先我们先分析一下，整个布局结构上面是百度的 **MapView** (地图区域)下面是一个 **ListView**(选址列表区域) ，乍一看好像是如下图描述一样：

	![布局](http://img.blog.csdn.net/20151012132925522)

	```
	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:orientation="vertical">
	
	    <com.baidu.mapapi.map.MapView
	        android:id="@+id/main_bdmap"
	        android:layout_width="match_parent"
	        android:layout_height="250dp"
	        android:onClick="true"></com.baidu.mapapi.map.MapView>
	
	    <ListView
	        android:id="@+id/main_pois"
	        android:layout_width="match_parent"
	        android:layout_height="0dp"
	        android:layout_weight="1"></ListView>
	
	</LinearLayout>
	```

	从布局中我们可以看到 **MapView** 占了250dp(大家自己可以随意给个值，不要太小即可)。下面的 **ListView** 则是填充了剩余的空间。

- ####第一步：基础地图

	```
	public class MainActivity extends AppCompatActivity {
	
	    private MapView mMapView;
	    private BaiduMap mBaiduMap;
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
	        //注意该方法要再setContentView方法之前实现
	        SDKInitializer.initialize(getApplicationContext());
	        //requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_main);
	        initView();
	    }
	
	    private void initView() {
	        mMapView = (MapView) findViewById(R.id.main_bdmap);
	        mBaiduMap = mMapView.getMap();
	    }
	    
        @Override
	    protected void onResume() {
	        super.onResume();
	        // activity 恢复时同时恢复地图控件
	        mMapView.onResume();
	    }
	
	    @Override
	    protected void onPause() {
	        super.onPause();
	        // activity 暂停时同时暂停地图控件
	        mMapView.onPause();
	    }

	    @Override
	    protected void onDestroy() {
	        super.onDestroy();
	
	        // activity 销毁时同时销毁地图控件
	        mMapView.onDestroy();
	        mMapView = null;
	    }
	    
	}
	```

	这里我们的 **MainActivity** 继承的是 **AppCompatActivity**，继承 **Activity** 也行。
	```
	//在使用SDK各组件之前初始化context信息，传入ApplicationContext
	//注意该方法要再setContentView方法之前实现
	SDKInitializer.initialize(getApplicationContext());
	```
	这段代码一段要放在 **setContentView(R.layout.activity_main);** 之前。继续往下看，其中有一句代码`requestWindowFeature(Window.FEATURE_NO_TITLE);`被注释掉了，需要去掉标题栏的朋友可以加上这句代码。下面呢还有另外一种方法去掉标题栏，打开我们的 **style.xml** 。
	
	![没改之前](http://img.blog.csdn.net/20150928180045316)

	改成 **Theme.AppCompat.Light.NoActionBar**

	![改完之后](http://img.blog.csdn.net/20150928174607806)

	运行效果图：

	![基础地图](http://img.blog.csdn.net/20150928180533707)

- ####第二步：定位

	继续书写我们的MainActivity里面的代码：

	```
	public class MainActivity extends AppCompatActivity implements BDLocationListener, OnGetGeoCoderResultListener, BaiduMap.OnMapStatusChangeListener {
	
	    private MapView mMapView;
	    private BaiduMap mBaiduMap;
	    private ListView poisLL;
	    /**
	     * 定位模式
	     */
	    private MyLocationConfiguration.LocationMode mCurrentMode;
	    /**
	     * 定位端
	     */
	    private LocationClient mLocClient;
	    /**
	     * 是否是第一次定位
	     */
	    private boolean isFirstLoc = true;
	    /**
	     * 定位坐标
	     */
	    private LatLng locationLatLng;
	    /**
	     * 定位城市
	     */
	    private String city;
	    /**
	     * 反地理编码
	     */
	    private GeoCoder geoCoder;
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
	        //注意该方法要再setContentView方法之前实现
	        SDKInitializer.initialize(getApplicationContext());
	        //requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_main);
	        initView();
	    }
	
	    private void initView() {
	        mMapView = (MapView) findViewById(R.id.main_bdmap);
	        mBaiduMap = mMapView.getMap();
	
	        poisLL = (ListView) findViewById(R.id.main_pois);
	
	        //定义地图状态
	        MapStatus mMapStatus = new MapStatus.Builder().zoom(18).build();
	        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
	        //改变地图状态
	        mBaiduMap.setMapStatus(mMapStatusUpdate);
	
	        //地图状态改变相关监听
	        mBaiduMap.setOnMapStatusChangeListener(this);
	
	        //开启定位图层
	        mBaiduMap.setMyLocationEnabled(true);
	
	        //定位图层显示方式
	        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
	
	        /**
	         * 设置定位图层配置信息，只有先允许定位图层后设置定位图层配置信息才会生效
	         * customMarker用户自定义定位图标
	         * enableDirection是否允许显示方向信息
	         * locationMode定位图层显示方式
	         */
	        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode, true, null));
	
	        //初始化定位
	        mLocClient = new LocationClient(this);
	        //注册定位监听
	        mLocClient.registerLocationListener(this);
	
	        //定位选项
	        LocationClientOption option = new LocationClientOption();
	        /**
	         * coorType - 取值有3个：
	         * 返回国测局经纬度坐标系：gcj02
	         * 返回百度墨卡托坐标系 ：bd09
	         * 返回百度经纬度坐标系 ：bd09ll
	         */
	        option.setCoorType("bd09ll");
	        //设置是否需要地址信息，默认为无地址
	        option.setIsNeedAddress(true);
	        //设置是否需要返回位置语义化信息，可以在BDLocation.getLocationDescribe()中得到数据，ex:"在天安门附近"， 可以用作地址信息的补充
	        option.setIsNeedLocationDescribe(true);
	        //设置是否需要返回位置POI信息，可以在BDLocation.getPoiList()中得到数据
	        option.setIsNeedLocationPoiList(true);
	        /**
	         * 设置定位模式
	         * Battery_Saving
	         * 低功耗模式
	         * Device_Sensors
	         * 仅设备(Gps)模式
	         * Hight_Accuracy
	         * 高精度模式
	         */
	        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
	        //设置是否打开gps进行定位
	        option.setOpenGps(true);
	        //设置扫描间隔，单位是毫秒 当<1000(1s)时，定时定位无效
	        option.setScanSpan(1000);
	
	        //设置 LocationClientOption
	        mLocClient.setLocOption(option);
	
	        //开始定位
	        mLocClient.start();
	
	
	    }
	
	    /**
	     * 定位监听
	     *
	     * @param bdLocation
	     */
	    @Override
	    public void onReceiveLocation(BDLocation bdLocation) {
	
	        //如果bdLocation为空或mapView销毁后不再处理新数据接收的位置
	        if (bdLocation == null || mBaiduMap == null) {
	            return;
	        }
	
	        //定位数据
	        MyLocationData data = new MyLocationData.Builder()
	                //定位精度bdLocation.getRadius()
	                .accuracy(bdLocation.getRadius())
	                        //此处设置开发者获取到的方向信息，顺时针0-360
	                .direction(bdLocation.getDirection())
	                        //经度
	                .latitude(bdLocation.getLatitude())
	                        //纬度
	                .longitude(bdLocation.getLongitude())
	                        //构建
	                .build();
	
	        //设置定位数据
	        mBaiduMap.setMyLocationData(data);
	
	        //是否是第一次定位
	        if (isFirstLoc) {
	            isFirstLoc = false;
	            LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
	            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLngZoom(ll, 18);
	            mBaiduMap.animateMapStatus(msu);
	        }
	
	        //获取坐标，待会用于POI信息点与定位的距离
	        locationLatLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
	        //获取城市，待会用于POISearch
	        city = bdLocation.getCity();
	
	        //创建GeoCoder实例对象
	        geoCoder = GeoCoder.newInstance();
	        //发起反地理编码请求(经纬度->地址信息)
	        ReverseGeoCodeOption reverseGeoCodeOption = new ReverseGeoCodeOption();
	        //设置反地理编码位置坐标
	        reverseGeoCodeOption.location(new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude()));
	        geoCoder.reverseGeoCode(reverseGeoCodeOption);
	
	        //设置查询结果监听者
	        geoCoder.setOnGetGeoCodeResultListener(this);
	    }
	
	    //地理编码查询结果回调函数
	    @Override
	    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
	    }
	
	    //反地理编码查询结果回调函数
	    @Override
	    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
	        List<PoiInfo> poiInfos = reverseGeoCodeResult.getPoiList();
	        PoiAdapter poiAdapter = new PoiAdapter(MainActivity.this, poiInfos);
	        poisLL.setAdapter(poiAdapter);
	    }
	
	
	    /**
	     * 手势操作地图，设置地图状态等操作导致地图状态开始改变
	     *
	     * @param mapStatus 地图状态改变开始时的地图状态
	     */
	    @Override
	    public void onMapStatusChangeStart(MapStatus mapStatus) {
	    }
	
	    /**
	     * 地图状态变化中
	     *
	     * @param mapStatus 当前地图状态
	     */
	    @Override
	    public void onMapStatusChange(MapStatus mapStatus) {
	    }
	
	    /**
	     * 地图状态改变结束
	     *
	     * @param mapStatus 地图状态改变结束后的地图状态
	     */
	    @Override
	    public void onMapStatusChangeFinish(MapStatus mapStatus) {
	        //地图操作的中心点
	        LatLng cenpt = mapStatus.target;
	        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(cenpt));
	    }
	
	    //回退键
	    @Override
	    public void onBackPressed() {
	        finish();
	    }
	
	    @Override
	    protected void onResume() {
	        super.onResume();
	        // activity 恢复时同时恢复地图控件
	        mMapView.onResume();
	    }
	
	    @Override
	    protected void onPause() {
	        super.onPause();
	        // activity 暂停时同时暂停地图控件
	        mMapView.onPause();
	    }
	
	    @Override
	    protected void onDestroy() {
	        super.onDestroy();
	
	        //退出时停止定位
	        mLocClient.stop();
	        //退出时关闭定位图层
	        mBaiduMap.setMyLocationEnabled(false);
	
	        // activity 销毁时同时销毁地图控件
	        mMapView.onDestroy();
	
	        //释放资源
	        if (geoCoder != null) {
	            geoCoder.destroy();
	        }
	
	        mMapView = null;
	    }
	}
	
	```

	代码分段分析：
	
	![放大地图](http://img.blog.csdn.net/20151012145427898)

	这里我们放大了地图，zoom(18)【地图缩放级别 3~20】，接下来对我们的定位选项做一个简单的说明：

	![定位选项](http://img.blog.csdn.net/20151012153822773)

	当我们定位完了之后，我们就可以对定位好的数据进行处理了，简单说明一下我们的定位监听做了哪些事情：

	![定位监听](http://img.blog.csdn.net/20151012160309478)

	定位好了之后我们进行过反地理编码，下面说明一下反地理编码监听里面做了哪些事情：

	![反地理编码监听](http://img.blog.csdn.net/20151012161733870)

- ####PoiAdapter

	这里会用到一张图 **baidumap_ico_poi_on.png** 大家右键另存为就行了：

	![baidumap_ico_poi_on](http://img.blog.csdn.net/20151012172459488)

	适配器视图 **locationpois.xml** ：

	```
	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:orientation="vertical"
	    android:paddingLeft="5dp">
	
	    <LinearLayout
	        android:id="@+id/locationpois_linearlayout"
	        android:gravity="center_vertical"
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content"
	        android:orientation="horizontal">
	
	        <TextView
	            android:id="@+id/locationpois_name"
	            android:layout_width="wrap_content"
	            android:layout_height="wrap_content" />
	    </LinearLayout>
	
	    <TextView
	        android:id="@+id/locationpois_address"
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content" />
	
	</LinearLayout>
	```
	布局简单说明：

	![布局说明](http://img.blog.csdn.net/20151012165457802)

	实现类 **PoiAdapter** ：

	```
	public class PoiAdapter extends BaseAdapter {
	    private Context context;
	    private List<PoiInfo> pois;
	    private LinearLayout linearLayout;
	
	
	    PoiAdapter(Context context, List<PoiInfo> pois) {
	        this.context = context;
	        this.pois = pois;
	    }
	
	    @Override
	    public int getCount() {
	        return pois.size();
	    }
	
	    @Override
	    public Object getItem(int position) {
	        return pois.get(position);
	    }
	
	    @Override
	    public long getItemId(int position) {
	        return position;
	    }
	
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ViewHolder holder = null;
	        if (convertView == null) {
	            convertView = LayoutInflater.from(context).inflate(R.layout.locationpois_item, null);
	            linearLayout = (LinearLayout) convertView.findViewById(R.id.locationpois_linearlayout);
	            holder = new ViewHolder(convertView);
	            convertView.setTag(holder);
	        } else {
	            holder = (ViewHolder) convertView.getTag();
	        }
	        if (position == 0 && linearLayout.getChildCount() < 2) {
	            ImageView imageView = new ImageView(context);
	            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(32, 32);
	            imageView.setLayoutParams(params);
	            imageView.setBackgroundColor(Color.TRANSPARENT);
	            imageView.setImageResource(R.mipmap.baidumap_ico_poi_on);
	            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
	            linearLayout.addView(imageView, 0, params);
	            holder.locationpoi_name.setTextColor(Color.parseColor("#FF5722"));
	        }
	        PoiInfo poiInfo = pois.get(position);
	        holder.locationpoi_name.setText(poiInfo.name);
	        holder.locationpoi_address.setText(poiInfo.address);
	        return convertView;
	    }
	
	    class ViewHolder {
	        TextView locationpoi_name;
	        TextView locationpoi_address;
	
	        ViewHolder(View view) {
	            locationpoi_name = (TextView) view.findViewById(R.id.locationpois_name);
	            locationpoi_address = (TextView) view.findViewById(R.id.locationpois_address);
	        }
	    }
	}
	```
	代码分段分析：

	![poiAdapter具体实现](http://img.blog.csdn.net/20151012170709170)

- ####地图状态变化---OnMapStatusChangeListener

	![地图状态变化](http://img.blog.csdn.net/20151012171315757)

	来看看我们现在运行是什么样子的：

	![第一次运行](http://img.blog.csdn.net/20151012171713293)

	大家移动一下地图试试。

- ####第三步：添加定位图标

	更改 **activity_main.xml** 布局文件：

	```
	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:orientation="vertical">
	
	    <LinearLayout
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:orientation="vertical">
	
	        <RelativeLayout
	            android:id="@+id/main_top_RL"
	            android:layout_width="match_parent"
	            android:layout_height="250dp">
	
	            <com.baidu.mapapi.map.MapView
	                android:id="@+id/main_bdmap"
	                android:layout_width="match_parent"
	                android:layout_height="match_parent"
	                android:onClick="true"></com.baidu.mapapi.map.MapView>
	
	            <ImageView
	                android:layout_width="wrap_content"
	                android:layout_height="wrap_content"
	                android:layout_centerInParent="true"
	                android:background="@android:color/transparent"
	                android:src="@mipmap/baidumap_ico_poi_on" />
	        </RelativeLayout>
	
	        <ListView
	            android:id="@+id/main_pois"
	            android:layout_width="match_parent"
	            android:layout_height="0dp"
	            android:layout_weight="1"></ListView>
	    </LinearLayout>
	
	</LinearLayout>
	```

	![居中图标](http://img.blog.csdn.net/20151012173056316)

	此次没有任何实现代码添加或者改动，运行看一下效果：

	![第二次运行](http://img.blog.csdn.net/20151012173650964)

	大家移动一下地图试试。

- ####输入关键字显示相关地址列表

	首先我们先更改 **activity_main.xml** ：
	
	```
	<?xml version="1.0" encoding="utf-8"?>
	<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent">
	
	    <LinearLayout
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:orientation="vertical">
	
	        <RelativeLayout
	            android:id="@+id/main_top_RL"
	            android:layout_width="match_parent"
	            android:layout_height="250dp">
	
	            <com.baidu.mapapi.map.MapView
	                android:id="@+id/main_bdmap"
	                android:layout_width="match_parent"
	                android:layout_height="match_parent"
	                android:onClick="true"></com.baidu.mapapi.map.MapView>
	
	            <ImageView
	                android:layout_width="wrap_content"
	                android:layout_height="wrap_content"
	                android:layout_centerInParent="true"
	                android:background="@android:color/transparent"
	                android:src="@mipmap/baidumap_ico_poi_on" />
	        </RelativeLayout>
	
	        <ListView
	            android:id="@+id/main_pois"
	            android:layout_width="match_parent"
	            android:layout_height="0dp"
	            android:layout_weight="1"></ListView>
	    </LinearLayout>
	
	    <LinearLayout
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:orientation="vertical">
	
	        <LinearLayout
	            android:layout_width="match_parent"
	            android:layout_height="40dp"
	            android:background="#ffcccccc"
	            android:gravity="center"
	            android:orientation="horizontal">
	
	            <EditText
	                android:id="@+id/main_search_address"
	                android:layout_width="match_parent"
	                android:layout_height="match_parent"
	                android:background="@android:color/transparent"
	                android:hint="请输入地址" />
	
	        </LinearLayout>
	
	        <ListView
	            android:id="@+id/main_search_pois"
	            android:layout_width="match_parent"
	            android:layout_height="0dp"
	            android:layout_weight="1"
	            android:background="#ffcccccc"
	            android:visibility="gone"></ListView>
	    </LinearLayout>
	
	
	</RelativeLayout>
	```

	代码说明：

	![主布局](http://img.blog.csdn.net/20151013100505702)

	接下来书写适配器的item布局 **poisearch_item.xml**：

	```
	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:orientation="vertical">
	
	    <TextView
	        android:id="@+id/poisearch_name"
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content" />
	
	    <LinearLayout
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content"
	        android:orientation="horizontal">
	
	        <TextView
	            android:id="@+id/poisearch_address"
	            android:layout_width="0dp"
	            android:layout_height="wrap_content"
	            android:layout_weight="1" />
	
	        <TextView
	            android:id="@+id/poisearch_distance"
	            android:layout_width="wrap_content"
	            android:layout_height="wrap_content" />
	    </LinearLayout>
	
	</LinearLayout>
	```

	代码说明：

	![适配器说明](http://img.blog.csdn.net/20151013101804504)

	最终更改我们的 **MainActivity** 里面的代码 **(请结合下面代码说明来看)** ：

	```
	public class MainActivity extends AppCompatActivity implements BDLocationListener, OnGetGeoCoderResultListener, BaiduMap.OnMapStatusChangeListener, TextWatcher {
	
	    private MapView mMapView;
	    private BaiduMap mBaiduMap;
	    private ListView poisLL;
	    /**
	     * 定位模式
	     */
	    private MyLocationConfiguration.LocationMode mCurrentMode;
	    /**
	     * 定位端
	     */
	    private LocationClient mLocClient;
	    /**
	     * 是否是第一次定位
	     */
	    private boolean isFirstLoc = true;
	    /**
	     * 定位坐标
	     */
	    private LatLng locationLatLng;
	    /**
	     * 定位城市
	     */
	    private String city;
	    /**
	     * 反地理编码
	     */
	    private GeoCoder geoCoder;
	    /**
	     * 界面上方布局
	     */
	    private RelativeLayout topRL;
	    /**
	     * 搜索地址输入框
	     */
	    private EditText searchAddress;
	    /**
	     * 搜索输入框对应的ListView
	     */
	    private ListView searchPois;
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
	        //注意该方法要再setContentView方法之前实现
	        SDKInitializer.initialize(getApplicationContext());
	        //requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_main);
	        initView();
	    }
	
	    private void initView() {
	        mMapView = (MapView) findViewById(R.id.main_bdmap);
	        mBaiduMap = mMapView.getMap();
	
	        poisLL = (ListView) findViewById(R.id.main_pois);
	
	        topRL = (RelativeLayout) findViewById(R.id.main_top_RL);
	
	        searchAddress = (EditText) findViewById(R.id.main_search_address);
	
	        searchPois = (ListView) findViewById(R.id.main_search_pois);
	
	        //定义地图状态
	        MapStatus mMapStatus = new MapStatus.Builder().zoom(18).build();
	        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
	        //改变地图状态
	        mBaiduMap.setMapStatus(mMapStatusUpdate);
	
	        //地图状态改变相关监听
	        mBaiduMap.setOnMapStatusChangeListener(this);
	
	        //开启定位图层
	        mBaiduMap.setMyLocationEnabled(true);
	
	        //定位图层显示方式
	        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
	
	        /**
	         * 设置定位图层配置信息，只有先允许定位图层后设置定位图层配置信息才会生效
	         * customMarker用户自定义定位图标
	         * enableDirection是否允许显示方向信息
	         * locationMode定位图层显示方式
	         */
	        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode, true, null));
	
	        //初始化定位
	        mLocClient = new LocationClient(this);
	        //注册定位监听
	        mLocClient.registerLocationListener(this);
	
	        //定位选项
	        LocationClientOption option = new LocationClientOption();
	        /**
	         * coorType - 取值有3个：
	         * 返回国测局经纬度坐标系：gcj02
	         * 返回百度墨卡托坐标系 ：bd09
	         * 返回百度经纬度坐标系 ：bd09ll
	         */
	        option.setCoorType("bd09ll");
	        //设置是否需要地址信息，默认为无地址
	        option.setIsNeedAddress(true);
	        //设置是否需要返回位置语义化信息，可以在BDLocation.getLocationDescribe()中得到数据，ex:"在天安门附近"， 可以用作地址信息的补充
	        option.setIsNeedLocationDescribe(true);
	        //设置是否需要返回位置POI信息，可以在BDLocation.getPoiList()中得到数据
	        option.setIsNeedLocationPoiList(true);
	        /**
	         * 设置定位模式
	         * Battery_Saving
	         * 低功耗模式
	         * Device_Sensors
	         * 仅设备(Gps)模式
	         * Hight_Accuracy
	         * 高精度模式
	         */
	        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
	        //设置是否打开gps进行定位
	        option.setOpenGps(true);
	        //设置扫描间隔，单位是毫秒 当<1000(1s)时，定时定位无效
	        option.setScanSpan(1000);
	
	        //设置 LocationClientOption
	        mLocClient.setLocOption(option);
	
	        //开始定位
	        mLocClient.start();
	
	    }
	
	    /**
	     * 定位监听
	     *
	     * @param bdLocation
	     */
	    @Override
	    public void onReceiveLocation(BDLocation bdLocation) {
	
	        //如果bdLocation为空或mapView销毁后不再处理新数据接收的位置
	        if (bdLocation == null || mBaiduMap == null) {
	            return;
	        }
	
	        //定位数据
	        MyLocationData data = new MyLocationData.Builder()
	                //定位精度bdLocation.getRadius()
	                .accuracy(bdLocation.getRadius())
	                        //此处设置开发者获取到的方向信息，顺时针0-360
	                .direction(bdLocation.getDirection())
	                        //经度
	                .latitude(bdLocation.getLatitude())
	                        //纬度
	                .longitude(bdLocation.getLongitude())
	                        //构建
	                .build();
	
	        //设置定位数据
	        mBaiduMap.setMyLocationData(data);
	
	        //是否是第一次定位
	        if (isFirstLoc) {
	            isFirstLoc = false;
	            LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
	            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLngZoom(ll, 18);
	            mBaiduMap.animateMapStatus(msu);
	        }
	
	        //获取坐标，待会用于POI信息点与定位的距离
	        locationLatLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
	        //获取城市，待会用于POISearch
	        city = bdLocation.getCity();
	
	        //文本输入框改变监听，必须在定位完成之后
	        searchAddress.addTextChangedListener(this);
	
	        //创建GeoCoder实例对象
	        geoCoder = GeoCoder.newInstance();
	        //发起反地理编码请求(经纬度->地址信息)
	        ReverseGeoCodeOption reverseGeoCodeOption = new ReverseGeoCodeOption();
	        //设置反地理编码位置坐标
	        reverseGeoCodeOption.location(new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude()));
	        geoCoder.reverseGeoCode(reverseGeoCodeOption);
	
	        //设置查询结果监听者
	        geoCoder.setOnGetGeoCodeResultListener(this);
	    }
	
	    //地理编码查询结果回调函数
	    @Override
	    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
	    }
	
	    //反地理编码查询结果回调函数
	    @Override
	    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
	        List<PoiInfo> poiInfos = reverseGeoCodeResult.getPoiList();
	        PoiAdapter poiAdapter = new PoiAdapter(MainActivity.this, poiInfos);
	        poisLL.setAdapter(poiAdapter);
	    }
	
	
	    /**
	     * 手势操作地图，设置地图状态等操作导致地图状态开始改变
	     *
	     * @param mapStatus 地图状态改变开始时的地图状态
	     */
	    @Override
	    public void onMapStatusChangeStart(MapStatus mapStatus) {
	    }
	
	    /**
	     * 地图状态变化中
	     *
	     * @param mapStatus 当前地图状态
	     */
	    @Override
	    public void onMapStatusChange(MapStatus mapStatus) {
	    }
	
	    /**
	     * 地图状态改变结束
	     *
	     * @param mapStatus 地图状态改变结束后的地图状态
	     */
	    @Override
	    public void onMapStatusChangeFinish(MapStatus mapStatus) {
	        //地图操作的中心点
	        LatLng cenpt = mapStatus.target;
	        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(cenpt));
	    }
	
	    /**
	     * 输入框监听---输入之前
	     *
	     * @param s     字符序列
	     * @param start 开始
	     * @param count 总计
	     * @param after 之后
	     */
	    @Override
	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }
	
	    /**
	     * 输入框监听---正在输入
	     *
	     * @param s      字符序列
	     * @param start  开始
	     * @param before 之前
	     * @param count  总计
	     */
	    @Override
	    public void onTextChanged(CharSequence s, int start, int before, int count) {
	    }
	
	    /**
	     * 输入框监听---输入完毕
	     *
	     * @param s
	     */
	    @Override
	    public void afterTextChanged(Editable s) {
	        if (s.length() == 0 || "".equals(s.toString())) {
	            searchPois.setVisibility(View.GONE);
	        } else {
	            //创建PoiSearch实例
	            PoiSearch poiSearch = PoiSearch.newInstance();
	            //城市内检索
	            PoiCitySearchOption poiCitySearchOption = new PoiCitySearchOption();
	            //关键字
	            poiCitySearchOption.keyword(s.toString());
	            //城市
	            poiCitySearchOption.city(city);
	            //设置每页容量，默认为每页10条
	            poiCitySearchOption.pageCapacity(10);
	            //分页编号
	            poiCitySearchOption.pageNum(1);
	            poiSearch.searchInCity(poiCitySearchOption);
	            //设置poi检索监听者
	            poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
	                //poi 查询结果回调
	                @Override
	                public void onGetPoiResult(PoiResult poiResult) {
	                    List<PoiInfo> poiInfos = poiResult.getAllPoi();
	                    PoiSearchAdapter poiSearchAdapter = new PoiSearchAdapter(MainActivity.this, poiInfos, locationLatLng);
	                    searchPois.setVisibility(View.VISIBLE);
	                    searchPois.setAdapter(poiSearchAdapter);
	                }
	
	                //poi 详情查询结果回调
	                @Override
	                public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
	                }
	            });
	        }
	    }
	
	
	    //回退键
	    @Override
	    public void onBackPressed() {
	        finish();
	    }
	
	    @Override
	    protected void onResume() {
	        super.onResume();
	        // activity 恢复时同时恢复地图控件
	        mMapView.onResume();
	    }
	
	    @Override
	    protected void onPause() {
	        super.onPause();
	        // activity 暂停时同时暂停地图控件
	        mMapView.onPause();
	    }
	
	    @Override
	    protected void onDestroy() {
	        super.onDestroy();
	
	        //退出时停止定位
	        mLocClient.stop();
	        //退出时关闭定位图层
	        mBaiduMap.setMyLocationEnabled(false);
	
	        // activity 销毁时同时销毁地图控件
	        mMapView.onDestroy();
	
	        //释放资源
	        if (geoCoder != null) {
	            geoCoder.destroy();
	        }
	
	        mMapView = null;
	    }
	
	}
	```
	代码说明：

	![最终说明](http://img.blog.csdn.net/20151013104225358)

	最终运行效果与文章开篇展示效果一样，这里呢就不再重复贴图了。

- ####GitHub

	最终项目GitHub地址：https://github.com/scp504677840/MoveMapLocation.git

