package com.scu.xing.tooth.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by wang on 2017/8/26.
 */

public class DBHelper extends SQLiteOpenHelper{

    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    //每次你对数据表进行编辑，添加时候，你都需要对数据库的版本进行提升

    //数据库版本
    private static final int DATABASE_VERSION=2;

    //数据库名称
    private static final String DATABASE_NAME="tooth.db";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    /**
     * 首次创建数据库时调用，一般写建库，建表的操作
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        //创建数据表
        String sql = "create table tooth_table(_id INTEGER PRIMARY KEY AUTOINCREMENT,key varchar(1000),name varchar(30),diagnosis varchar(1000))";
        // 可以在这按行读取文件 ************
        db.execSQL(sql);

        //插入数据
        insertData(db);

        Log.i("info", "create Database------------->");

    }

    /**
     * 当数据库的表发生变化时，会自动执行
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //如果旧表存在，删除，所以数据将会消失
        db.execSQL("DROP TABLE IF EXISTS tooth_table");

        //再次创建表
        onCreate(db);
    }

    /**
     * 插入原始数据
     */
    public void insertData(SQLiteDatabase db){

        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('浅龋','激发痛-自发痛-疼痛定位-牙髓电活力测验+','鉴别诊断：呈白垩色点或斑；探针检查有粗糙感或能勾住探针尖端；一般无主观症状；遇物理、化学刺激无明显反应。')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('中龋','激发痛+自发痛-冷刺激+牙体硬组织病损+疼痛定位-牙髓电活力测验+','鉴别诊断：黄褐色或深褐色；对酸甜饮食敏感，过冷过热饮食也能产生酸痛感觉，冷刺激尤为明显，刺激去除后症状立即消失；部分患者可无主观症状。')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('深龋','自发痛+牙体硬组织病损+激发痛+冷刺激+热刺激+食物嵌塞+疼痛定位-牙髓电活力测验+' ,'鉴别诊断：可见深龋洞；食物压迫致使牙髓疼痛；冷热和化学刺激，疼痛感较中龋更剧烈；牙髓组织修复性反应，X线可见。')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('牙震荡','叩痛+自发痛-牙松动+牙髓电活力测验+牙髓电活力测验-','鉴别诊断：可有伸长不适感；若受伤后无反应，可能数周后恢复；若伤后有反应，后无反应，同时牙变色，不可恢复。')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('牙脱位','自发痛+牙松动+牙移位+牙髓电活力测验+牙髓电活力测验-','鉴别诊断：①部分脱位：若向根方嵌入，临床牙冠变短；可出现咬合障碍，常见牙龈撕裂、牙槽突骨折。X线：牙根尖与牙槽窝间隙明显增宽。②完全脱位：牙完全离体或仅有少许软组织相连，牙槽窝空虚；常见牙龈撕裂、牙槽突骨折。')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('牙折','牙髓电活力测验-自发痛+自发痛-牙齿敏感+牙齿敏感-','鉴别诊断：X线片显示折裂线。')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('磨损','牙齿敏感+自发痛+自发痛-食物嵌塞+牙体硬组织病损+牙髓电活力测验+' ,'鉴别诊断：① 可伴有TMJ功能紊乱综合征：关节弹响、运动异常、疼痛；② 可伴有牙体折裂、咬伤软组织；③ 严重者可见牙髓根尖周病变' )");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('酸蚀症','自发痛+自发痛-牙齿敏感+牙体硬组织病损+牙髓电活力测验+' ,'鉴别诊断：① 盐酸所致者：自切缘向唇面光滑斜面，硬而无变色，切断可折断。② 硝酸所致者：多发于牙颈部，白垩状，染色黄褐或灰色，质地松软，易崩碎。③ 硫酸所致者：不易引起酸蚀，仅有酸涩感。④ 常有胃酸反流者：后牙牙合面与腭面的凹陷性损害。⑤ 其他低浓度酸：一般发生于釉牙骨质界。')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('楔状缺损','牙体硬组织病损+激发痛+牙齿敏感+牙髓电活力测验+' ,'鉴别诊断：患者多无牙周病，发生于口角附近的牙齿唇颊面颈部。')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('牙隐裂','自发痛-咬合痛+咀嚼痛+牙髓电活力测验+牙齿敏感+' ,'鉴别诊断：好发部位于第一磨牙，其次是第二磨牙和前磨牙；牙髓炎、根尖周炎的患牙，叩痛明显，但未发现龋坏、缺损等牙体硬组织病应考虑')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('牙根纵裂','咀嚼痛+自发痛+激发痛+叩痛+牙髓电活力测验+' ,'鉴别诊断：X线片显示根裂，边缘整齐，不论其长度，均通过根尖孔')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('可复性牙髓炎','激发痛+冷刺激+自发痛-牙体硬组织病损+牙髓电活力测验+' ,'鉴别诊断：冷测牙面出现一过性敏感')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('急性牙髓炎','自发痛+激发痛+冷刺激+热刺激+牵涉痛+放散痛+疼痛定位-牙髓电活力测验+牙体硬组织病损+牙周袋+' ,'鉴别诊断：自发性尖锐疼痛，出现化脓时为搏动性跳痛以及“热痛冷缓解”；早期对冷刺激敏感，出现化脓后，对热刺激敏感；多为夜间痛；疼痛不能自行定位，呈牵涉性或放散性，但仅放射至同侧。')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('慢性闭锁性牙髓炎','自发痛-自发痛+牙体硬组织病损+叩痛+穿髓孔-牙周袋+疼痛定位+牙髓电活力测验+' ,'鉴别诊断：阵发性隐痛、钝痛，有剧烈自发痛病史；长期冷、热刺激痛病史；洞内探诊感觉迟钝；无穿髓孔')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('慢性溃疡性牙髓炎','自发痛-激发痛+冷刺激+热刺激-穿髓孔+咀嚼痛+叩痛-牙体硬组织病损+疼痛定位+牙髓电活力测验+牙周袋+' ,'鉴别诊断：浅探不痛，深探剧痛且有暗色血液渗出；')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('慢增生性牙髓炎','自发痛-激发痛+激发痛-穿髓孔+牙体硬组织病损+疼痛定位+牙髓电活力测验+' ,'鉴别诊断：青少年多发；进食出血；牙髓息肉，探之无痛但易出血')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('残髓炎','自发痛+激发痛+冷刺激+热刺激+放散痛+叩痛+牙体硬组织病损+咬合痛+牙髓电活力测验-' ,'鉴别诊断：均有牙髓治疗病史；强温度刺激患牙有迟缓性疼痛')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('逆行性牙髓炎','自发痛+激发痛+冷刺激+热刺激+放散痛+夜间痛+牙周袋+牙体硬组织病损-叩痛+牙髓电活力测验+' ,'鉴别诊断：长时间牙周炎病史；可见口臭、牙松动、咬合无力等；同一牙齿不同部位温度测验结果不同；叩诊浊音；X线片可见广泛的牙周组织破坏或根分叉病变' )");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('牙髓坏死','牙髓电活力测验-自发痛-激发痛-牙冠变色+','鉴别诊断：牙冠变色，失去光泽；X线片显示根尖周影像无明显异常')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('牙髓钙化','自发痛+放散痛+牙冠变色+牙髓电活力测验+牙髓电活力测验-','鉴别诊断：可有自发痛，与体位有关，与温度刺激无关，可沿三叉神经区域放散，但牙髓治疗后疼痛消失；X线片可见髓腔内钙化物或弥散性阻射影像；牙冠变色，失去光泽；')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('牙内吸收','牙冠变色+牙髓电活力测验+牙髓电活力测验-','鉴别诊断：牙冠变色，呈粉红色；X线片可见髓腔内有局限性不规则膨大透影区域')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('急性浆液性根尖周炎','咬合痛+自发痛+自发痛-激发痛-疼痛定位+叩痛+牙周袋+牙体硬组织病损+牙冠变色+牙髓电活力测验-牙松动+' ,'鉴别诊断：早期无自发痛，有浮出、伸长感，咬紧缓解，后期为自发、持续的钝痛，咬紧加剧疼痛；叩诊（+~++），扪诊根尖部出现不适；牙松动（I）;X线片显示根尖周组织无明显异常；')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('急性化脓性根尖周炎','自发痛+激发痛-咬合痛+叩痛+牙松动+淋巴结肿大+牙髓电活力测验-疼痛定位+' ,'鉴别诊断：X线片根尖部牙槽骨无明显改变①根尖周脓肿：牙伸长感，叩诊（++~+++）；根尖部牙龈潮红，扪诊根尖部轻微疼痛；牙松动（II~III）;X线片显示根尖部牙槽骨无明显改变。②骨膜下脓肿：疼痛达最高峰，出现体温升高、乏力等全身症状；牙伸长松动，叩诊（+++）；根尖部牙龈红肿，压痛明显，扪诊根尖部有波动感；牙松动（III）；X线片显示根尖部牙槽骨无明显改变。③黏膜下脓肿：疼痛缓解，发热、乏力症状减轻；叩诊（+~++），牙松动（I）；黏膜肿胀局限，波动感明显，脓肿浅表。')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('慢性根尖周炎','自发痛-激发痛-牙冠变色+牙髓电活力测验-牙松动-叩诊-牙体硬组织病损+' ,'鉴别诊断：患牙可有咀嚼不适，患牙有牙髓病史、牙髓治疗史、反复肿痛史；可有窦道开口；X线片可鉴别一下类型①根尖周肉芽肿：根尖部小范围透射影，（小于1cm），界清，周围骨质正常或者稍致密。②根尖周脓肿：根尖区透射影边界不清，形状不规则，周围骨质疏松呈云雾状。③根尖周囊肿：较大的圆形透射区，边界清楚，周围有一圈致密的白线包绕。④根尖周致密性骨炎：根尖部骨质致密阻射影，无透射区，下颌后牙多见。')");
        db.execSQL("insert into tooth_table (name, key, diagnosis ) values ('根尖周致密性骨炎','自发痛-激发痛-牙冠变色+牙髓电活力测验-牙松动-','鉴别诊断：不易发现，无反复肿痛史，X线片显示根尖部骨质局限性致密阻射影像，无透射区，下颌后牙多见。')");

    }
}
