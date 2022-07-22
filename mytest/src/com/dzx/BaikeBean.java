package com.dzx;

import com.dzx.util.LUtils;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaikeBean {

    private String tag = getClass().getSimpleName();
    public String signatureServer;
    public List<ContentMyBean> vca_tag;

    public static class ContentMyBean {
        public String baikeDefaultLemma;
        public String baikeTags;
        public String tag_value;
        public BaikeDefaultLemmaBean mBaikeDefaultLemmaBean;
        public BaikeTagsBean mBaikeTagsBean;

        public static class BaikeTagsBean {
            /**
             * errno : 0
             * errmsg :
             * tags : ["行星"]
             */

            private int errno;
            private String errmsg;
            private List<String> tags;

            public int getErrno() {
                return errno;
            }

            public void setErrno(int errno) {
                this.errno = errno;
            }

            public String getErrmsg() {
                return errmsg;
            }

            public void setErrmsg(String errmsg) {
                this.errmsg = errmsg;
            }

            public List<String> getTags() {
                return tags;
            }

            public void setTags(List<String> tags) {
                this.tags = tags;
            }
        }

        public static class BaikeDefaultLemmaBean {


            /**
             * errno : 0
             * errmsg :
             * lemmaId : 6431
             * lemmaTitle : 地球
             * lemmaDesc : 太阳系八大行星之一
             * url : https://baike.baidu.com/item/%E5%9C%B0%E7%90%83/6431
             * summary : 地球（Earth）是太阳系八大行星之一，按离太阳由近及远的次序排为第三颗，也是太阳系中直径、质量和密度最大的类地行星，距离太阳1.5亿公里。地球自西向东自转，同时围绕太阳公转。现有40亿～46亿岁，它有一个天然卫星——月球，二者组成一个天体系统——地月系统。46亿年以前起源于原始太阳星云。地球赤道半径6378.137千米，极半径6356.752千米，平均半径约6371千米，赤道周长大约为40076千米，呈两极稍扁赤道略鼓的不规则的椭圆球体。地球表面积5.1亿平方公里，其中71%为海洋，29%为陆地，在太空上看地球呈蓝色。地球内部有核、幔、壳结构，地球外部有水圈、大气圈以及磁场。地球是目前宇宙中已知存在生命的唯一的天体，是包括人类在内上百万种生物的家园。
             * picUrl : https://bkimg.cdn.bcebos.com/pic/d1a20cf431adcbeff3bc945ea3af2edda2cc9fda
             * card : [{"key":"m36_cname","name":"中文名","value":["地球"]},{"key":"m36_ename","name":"外文名","value":["Earth"]},{"key":"m36_othername","name":"别称","value":["盖亚(Gaia)"]},{"key":"m36_classify","name":"分类","value":["行星"]},{"key":"m36_quality","name":"质量","value":["5.965×10^24kg"]},{"key":"m36_density","name":"平均密度","value":["5507.85kg/m3"]},{"key":"m36_diameter","name":"直径","value":["12756千米"]},{"key":"m36_surfacetemp","name":"表面温度","value":["15摄氏度（59华氏度）"]},{"key":"m36_escapevelocity","name":"逃逸速度","value":["11.2km/s（≅39,600 km/h）"]},{"key":"m36_Albedo","name":"反照率","value":["0.367"]},{"key":"m36_rotationperiod","name":"自转周期","value":["23h56min4s"]},{"key":"m36_ascension","name":"赤经","value":["未定义"]},{"key":"m36_declination","name":"赤纬","value":["+90°"]},{"key":"m36_semimajoraxis","name":"半长轴","value":["149,597,887.5km"]},{"key":"m36_eccentricity","name":"离心率","value":["0.016710219"]},{"key":"m36_orbitalperiod","name":"公转周期","value":["一年（365.24219天）"]},{"key":"m36_orbitalinclination","name":"轨道倾角","value":["0（7.25°至太阳赤道）"]},{"key":"m36_ext_0","name":"体积","value":["1.0832073×10^12km3"]},{"key":"m36_ext_1","name":"远日点距离","value":["152,097,701.0km"]},{"key":"m36_ext_2","name":"近日点距离","value":["147,098,074.0km"]},{"key":"m36_ext_3","name":"轨道周长","value":["924,375,700.0km"]},{"key":"m36_ext_4","name":"近日点辐角","value":["114.20783°"]},{"key":"m36_ext_5","name":"轨道半短轴","value":["149,576,999.826km"]},{"key":"m36_ext_6","name":"平均公转速度","value":["29.783km/s（107,218km/h）"]},{"key":"m36_ext_7","name":"最大公转速度","value":["30.287km/s（109,033km/h）"]},{"key":"m36_ext_8","name":"最小公转速度","value":["29.291km/s（105,448km/h）"]},{"key":"m36_ext_9","name":"卫星","value":["月球"]},{"key":"m36_ext_10","name":"宇宙速度","value":["11.186km/s（39600km/h）"]},{"key":"m36_ext_11","name":"恒星日","value":["0.997258d（23.934h)"]},{"key":"m36_ext_12","name":"赤道圆周长","value":["40,075.13km"]},{"key":"m36_ext_13","name":"纵横比","value":["0.9966471"]},{"key":"m36_ext_14","name":"赤道旋转速率","value":["465.11m/s"]}]
             * catalogContentStructured : [{"tag":"header","number":2,"title":"诞生和演化","length":5,"lines":1,"pid":"p-0","level":1,"index":"1"},{"tag":"paragraph","content":[{"tag":"text","text":"主词条："},{"tag":"innerlink","text":"地球历史","lemmaId":0,"lemmaTitle":"地球历史"},{"tag":"text","text":"，"},{"tag":"innerlink","text":"大陆漂移假说","lemmaId":0,"lemmaTitle":"大陆漂移假说"}],"length":15,"lines":1,"pid":"p-1","level":1,"index":"1"},{"tag":"paragraph","content":[{"tag":"text","text":"46亿年前，地球"},{"tag":"innerlink","text":"诞生","lemmaId":82555,"lemmaTitle":"诞生"},{"tag":"text","text":"了。地球演化大致可分为三个"},{"tag":"innerlink","text":"阶段","lemmaId":0,"lemmaTitle":"阶段"},{"tag":"text","text":"。"}],"length":26,"lines":2,"pid":"p-3","float":[{"tag":"module","type":"album","moduleId":13065065,"data":{"basic":{"modulename":"album","desc":"50亿年以前的太阳系","albumTag":"","url":"cover=aa59892bcbe998b4e7cd4019&count=4&desc=50%E4%BA%BF%E5%B9%B4%E4%BB%A5%E5%89%8D%E7%9A%84%E5%A4%AA%E9%98%B3%E7%B3%BB","total":4,"coverpic":"aa59892bcbe998b4e7cd4019","width":219,"height":170,"style":"float: right;width:219px;height:170px;","layout":"right","moduleId":13065065,"moduleVersionId":279356458,"moduleType":2,"picId":1320021535,"type":{"view":220,"albumview":200,"oriWithWater":220,"litterImage":200,"authModule":150,"view_new":209,"view_card":268},"sizes":{"220":{"url":"https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/watermark,g_7,image_d2F0ZXIvYmFpa2U2MA==,xp_5,yp_5","width":219,"height":170,"size":220},"200":{"url":"https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/resize,m_lfit,w_200,h_200,limit_1","width":200,"height":155,"size":200},"150":{"url":"https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/resize,m_lfit,w_150,limit_1","width":150,"height":116,"size":150},"209":{"url":"https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/resize,m_lfit,w_209,limit_1","width":209,"height":162,"size":209},"268":{"url":"https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/resize,m_lfit,w_268,limit_1","width":219,"height":170,"size":268}}},"toppics":[{"src":"aa59892bcbe998b4e7cd4019","owner":"","desc":"50亿年以前的太阳系","width":640,"height":480,"url":"https://bkimg.cdn.bcebos.com/pic/aa59892bcbe998b4e7cd4019?x-bce-process=image/resize,m_fill,w_600,h_600,align_50"},{"src":"42e89c26716a33338a82a198","owner":"1165774571","desc":"宇宙环境","height":387,"width":400,"url":"https://bkimg.cdn.bcebos.com/pic/42e89c26716a33338a82a198?x-bce-process=image/resize,m_fill,w_300,h_300,align_50"},{"src":"0df431adcbef7609922336242fdda3cc7cd99e65","owner":"1165774571","desc":"太阳变成红巨星后地球上看到的太阳景象","height":357,"width":562,"url":"https://bkimg.cdn.bcebos.com/pic/0df431adcbef7609922336242fdda3cc7cd99e65?x-bce-process=image/resize,m_fill,w_300,h_300,align_50"}],"toppicscount":3},"pid":"p-2"}],"level":1,"index":"1"},{"tag":"paragraph","content":[{"tag":"text","text":"第一阶段","bold":true},{"tag":"text","text":"为地球圈层形成"},{"tag":"innerlink","text":"时期","lemmaId":0,"lemmaTitle":"时期"},{"tag":"text","text":"，其时限大致距今4600至4200"},{"tag":"innerlink","text":"Ma","lemmaId":9141846,"lemmaTitle":"Ma"},{"tag":"text","text":"。46亿年前诞生时候的地球与"},{"tag":"innerlink","text":"21世纪","lemmaId":3864,"lemmaTitle":"21世纪"},{"tag":"text","text":"的大不相同。根据科学家推断，地球形成之初是一个由炽热液体物质（主要为岩浆）组成的炽热的球。随着时间的推移，地表的温度不断下降，固态的地核逐渐形成。密度大的物质向地心移动，密度小的物质（岩石等）浮在地球表面，这就形成了一个表面主要由岩石组成的地球。"}],"length":173,"lines":10,"pid":"p-4","level":1,"index":"1"},{"tag":"paragraph","content":[{"tag":"text","text":"第二阶段","bold":true},{"tag":"text","text":"为"},{"tag":"innerlink","text":"太古宙","lemmaId":0,"lemmaTitle":"太古宙"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"元古宙","lemmaId":0,"lemmaTitle":"元古宙"},{"tag":"text","text":"时期。其时限距今4200至543Ma。地球自不间断地向外释放能量，由高温岩浆不断喷发释放的水蒸气，二氧化碳等气体构成了非常稀薄的早期大气层\u2014\u2014原始大气。随着原始大气中的水蒸气的不断增多，越来越多的水蒸气凝结成小水滴，再汇聚成雨水落入地表。就这样，原始的海洋形成了。"}],"length":144,"lines":8,"pid":"p-5","level":1,"index":"1"},{"tag":"paragraph","content":[{"tag":"text","text":"第三阶段","bold":true},{"tag":"text","text":"为"},{"tag":"innerlink","text":"显生宙","lemmaId":0,"lemmaTitle":"显生宙"},{"tag":"text","text":"时期，其时限由543Ma至今。显生宙延续的时间相对短暂，但这一时期生物及其繁盛，地质演化十分"},{"tag":"innerlink","text":"迅速","lemmaId":0,"lemmaTitle":"迅速"},{"tag":"text","text":"，地质作用"},{"tag":"innerlink","text":"丰富多彩","lemmaId":0,"lemmaTitle":"丰富多彩"},{"tag":"text","text":"，加之地质体遍布全球各地，"},{"tag":"innerlink","text":"广泛","lemmaId":0,"lemmaTitle":"广泛"},{"tag":"text","text":"保存，可以极好的对其进行观察和研究，为地质"},{"tag":"innerlink","text":"科学","lemmaId":10406,"lemmaTitle":"科学"},{"tag":"text","text":"的主要"},{"tag":"innerlink","text":"研究","lemmaId":0,"lemmaTitle":"研究"},{"tag":"text","text":"对象，并建立起了地质学的基本理论和"},{"tag":"innerlink","text":"基础","lemmaId":32794,"lemmaTitle":"基础"},{"tag":"text","text":"知识。"}],"length":130,"lines":8,"pid":"p-6","level":1,"index":"1"},{"tag":"paragraph","content":[{"tag":"innerlink","text":"人类","lemmaId":31910,"lemmaTitle":"人类"},{"tag":"innerlink","text":"科学家","lemmaId":0,"lemmaTitle":"科学家"},{"tag":"text","text":"已经能够重建地球过去有关的"},{"tag":"innerlink","text":"资料","lemmaId":0,"lemmaTitle":"资料"},{"tag":"text","text":"。"},{"tag":"innerlink","text":"太阳系","lemmaId":0,"lemmaTitle":"太阳系"},{"tag":"text","text":"的物质起源于45.672亿±60万年前，而大约在45.4亿年前（误差约1%），地球和"},{"tag":"innerlink","text":"太阳系","lemmaId":0,"lemmaTitle":"太阳系"},{"tag":"text","text":"内的其他行星开始在太阳星云\u2014\u2014太阳形成后残留下来的气体与尘埃形成的圆盘状\u2014\u2014内形成。通过吸积的过程，地球经过1至2千万年的"},{"tag":"innerlink","text":"时间","lemmaId":25651,"lemmaTitle":"时间"},{"tag":"text","text":"，大致上已经完全成形。从最初"},{"tag":"innerlink","text":"熔融","lemmaId":0,"lemmaTitle":"熔融"},{"tag":"text","text":"的状态，地球的外层先冷却凝固成固体的"},{"tag":"innerlink","text":"地壳","lemmaId":0,"lemmaTitle":"地壳"},{"tag":"text","text":"，水也开始在大气层中累积。"},{"tag":"innerlink","text":"月亮","lemmaId":5557,"lemmaTitle":"月亮"},{"tag":"text","text":"形成的较晚，大约是45.3亿年前，一颗"},{"tag":"innerlink","text":"火星","lemmaId":5627,"lemmaTitle":"火星"},{"tag":"text","text":"大小，质量约为地球10%的天体（通常称为忒伊亚）与地球发生致命性的碰撞。这个天体的部分质量与地球结合，还有一部分飞溅入太空中，并且有足够的物质进入轨道形成了"},{"tag":"innerlink","text":"月球","lemmaId":30767,"lemmaTitle":"月球"},{"tag":"text","text":"。释放出的气体和"},{"tag":"innerlink","text":"火山","lemmaId":0,"lemmaTitle":"火山"},{"tag":"text","text":"的活动产生原始的"},{"tag":"innerlink","text":"大气层","lemmaId":0,"lemmaTitle":"大气层"},{"tag":"text","text":"，"},{"tag":"innerlink","text":"小行星","lemmaId":68902,"lemmaTitle":"小行星"},{"tag":"text","text":"、较大的"},{"tag":"innerlink","text":"原行星","lemmaId":0,"lemmaTitle":"原行星"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"彗星","lemmaId":22704,"lemmaTitle":"彗星"},{"tag":"text","text":"和"},{"tag":"innerlink","text":"海王星","lemmaId":30351,"lemmaTitle":"海王星"},{"tag":"text","text":"外天体等携带来的水，使地球的水分增加，冷凝的水产生海洋。新形成的太阳"},{"tag":"innerlink","text":"光度","lemmaId":0,"lemmaTitle":"光度"},{"tag":"text","text":"只有太阳的70%，但是有证据显示早期的海洋依然是液态的，这称为微弱年轻太阳谬论矛盾。"},{"tag":"innerlink","text":"温室效应","lemmaId":0,"lemmaTitle":"温室效应"},{"tag":"text","text":"和较高"},{"tag":"innerlink","text":"太阳活动","lemmaId":0,"lemmaTitle":"太阳活动"},{"tag":"text","text":"的组合，提高了地球表面的温度，阻止了海洋的凝结。"}],"length":436,"lines":25,"pid":"p-8","float":[{"tag":"module","type":"album","moduleId":3371547,"data":{"basic":{"modulename":"album","desc":"宇宙中看地球","albumTag":"","url":"cover=c995d143ad4bd11333122e5d5bafa40f4afb0585&count=21&desc=%E5%AE%87%E5%AE%99%E4%B8%AD%E7%9C%8B%E5%9C%B0%E7%90%83","total":21,"coverpic":"c995d143ad4bd11333122e5d5bafa40f4afb0585","width":220,"height":137,"style":"float: right;width:220px;height:137px;","layout":"right","moduleId":3371547,"moduleVersionId":279356457,"moduleType":2,"picId":5900132928,"type":{"view":220,"albumview":120,"oriWithWater":221,"litterImage":200,"authModule":150,"view_new":209,"view_card":268},"sizes":{"220":{"url":"https://bkimg.cdn.bcebos.com/pic/c995d143ad4bd11333122e5d5bafa40f4afb0585?x-bce-process=image/resize,m_lfit,w_220,h_220,limit_1","width":220,"height":137,"size":220},"120":{"url":"https://bkimg.cdn.bcebos.com/pic/c995d143ad4bd11333122e5d5bafa40f4afb0585?x-bce-process=image/resize,m_lfit,h_120,limit_1","width":192,"height":120,"size":120},"221":{"url":"https://bkimg.cdn.bcebos.com/pic/c995d143ad4bd11333122e5d5bafa40f4afb0585?x-bce-process=image/watermark,g_7,image_d2F0ZXIvYmFpa2U2MA==,xp_5,yp_5","width":220,"height":137,"size":221},"200":{"url":"https://bkimg.cdn.bcebos.com/pic/c995d143ad4bd11333122e5d5bafa40f4afb0585?x-bce-process=image/resize,m_lfit,w_200,h_200,limit_1","width":200,"height":124,"size":200},"150":{"url":"https://bkimg.cdn.bcebos.com/pic/c995d143ad4bd11333122e5d5bafa40f4afb0585?x-bce-process=image/resize,m_lfit,w_150,limit_1","width":150,"height":93,"size":150},"209":{"url":"https://bkimg.cdn.bcebos.com/pic/c995d143ad4bd11333122e5d5bafa40f4afb0585?x-bce-process=image/resize,m_lfit,w_209,limit_1","width":209,"height":130,"size":209},"268":{"url":"https://bkimg.cdn.bcebos.com/pic/c995d143ad4bd11333122e5d5bafa40f4afb0585?x-bce-process=image/resize,m_lfit,w_268,limit_1","width":220,"height":137,"size":268}}},"toppics":[{"src":"c995d143ad4bd11333122e5d5bafa40f4afb0585","owner":"","desc":"宇宙中看地球","width":220,"height":137,"url":"https://bkimg.cdn.bcebos.com/pic/c995d143ad4bd11333122e5d5bafa40f4afb0585?x-bce-process=image/resize,m_fill,w_600,h_600,align_50"},{"src":"6a600c338744ebf884bafd50d8f9d72a6159a792","owner":"冥血幽狼","desc":"宇宙中看地球","height":1024,"width":1024,"url":"https://bkimg.cdn.bcebos.com/pic/6a600c338744ebf884bafd50d8f9d72a6159a792?x-bce-process=image/resize,m_fill,w_300,h_300,align_0"},{"src":"58ee3d6d55fbb2fbcac8c17d4e4a20a44723dcfa","owner":"冥血幽狼","desc":"宇宙中看地球","height":640,"width":1024,"url":"https://bkimg.cdn.bcebos.com/pic/58ee3d6d55fbb2fbcac8c17d4e4a20a44723dcfa?x-bce-process=image/resize,m_fill,w_300,h_300,align_50"}],"toppicscount":3},"pid":"p-7"}],"level":1,"index":"1"},{"tag":"paragraph","content":[{"tag":"text","text":"有两个主要的理论提出"},{"tag":"innerlink","text":"大陆","lemmaId":0,"lemmaTitle":"大陆"},{"tag":"text","text":"的成长：稳定的成长到现代和在早期的历史中快速的成长。研究显示第二种学说比较可能，早期的"},{"tag":"innerlink","text":"地壳","lemmaId":0,"lemmaTitle":"地壳"},{"tag":"text","text":"是快速成长的，随后跟着长期稳定的大陆地区。在"},{"tag":"innerlink","text":"时间尺度","lemmaId":0,"lemmaTitle":"时间尺度"},{"tag":"text","text":"上的最后数亿年间，表面不断的重塑自己，大陆持续的形成和"},{"tag":"innerlink","text":"分裂","lemmaId":3470,"lemmaTitle":"分裂"},{"tag":"text","text":"。在表面迁徙的大陆，偶尔会结成"},{"tag":"innerlink","text":"超大陆","lemmaId":0,"lemmaTitle":"超大陆"},{"tag":"text","text":"。大约在7.5亿年前，已知最早的一个超大陆罗迪尼亚开始分裂，稍后又在6亿至5.4亿年时合并成"},{"tag":"innerlink","text":"潘诺西亚大陆","lemmaId":0,"lemmaTitle":"潘诺西亚大陆"},{"tag":"text","text":"，最后是1.8亿年前开始分裂的"},{"tag":"innerlink","text":"盘古大陆","lemmaId":0,"lemmaTitle":"盘古大陆"},{"tag":"text","text":"。"},{"tag":"ref","ctrId":"","index":1,"data":{"type":"2","author":"黄定华等","title":"普通地质学","publisher":"高等教育出版社","place":"北京","publishYear":"2004","refPage":"19-28","index":2}}],"length":202,"lines":12,"pid":"p-9","level":1,"index":"1"},{"tag":"header","number":2,"title":"地质时期","length":4,"lines":1,"pid":"p-10","level":1,"index":"2"},{"tag":"paragraph","content":[{"tag":"text","text":"主词条："},{"tag":"innerlink","text":"地质时期","lemmaId":0,"lemmaTitle":"地质时期"}],"length":8,"lines":1,"pid":"p-11","level":1,"index":"2"},{"tag":"paragraph","content":[{"tag":"text","text":"在地球演化过程中，发生一些天文与地质事件，将事件的时间段叫做"},{"tag":"innerlink","text":"地质时期","lemmaId":0,"lemmaTitle":"地质时期"},{"tag":"text","text":"。"}],"length":35,"lines":2,"pid":"p-12","level":1,"index":"2"},{"tag":"paragraph","content":[{"tag":"text","text":"在各地质时期，在与地球相关的宇宙空间及太阳系和地球所发生的大事件，在地球自身、"},{"tag":"innerlink","text":"地壳运动","lemmaId":0,"lemmaTitle":"地壳运动"},{"tag":"text","text":"、地层、岩石、构造、"},{"tag":"innerlink","text":"古生物","lemmaId":0,"lemmaTitle":"古生物"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"古地磁","lemmaId":0,"lemmaTitle":"古地磁"},{"tag":"text","text":"、冰川、古气候等多方面都留下了记录。"}],"length":78,"lines":5,"pid":"p-13","level":1,"index":"2"},{"tag":"paragraph","content":[{"tag":"text","text":"在不同的地质时期，地质作用不同，特征不同。"}],"length":21,"lines":2,"pid":"p-14","level":1,"index":"2"},{"tag":"paragraph","content":[{"tag":"text","text":"将地球历史划分为：地球形成时期、地壳形成时期、进入太阳系前时期、进入太阳系时期、"},{"tag":"innerlink","text":"地月系","lemmaId":0,"lemmaTitle":"地月系"},{"tag":"text","text":"形成时期、新生时期，见下表。"}],"length":57,"lines":4,"pid":"p-15","level":1,"index":"2"},{"tag":"paragraph","content":[{"tag":"text","text":"地质时期与特征表","bold":true}],"length":8,"lines":1,"pid":"p-16","level":1,"index":"2"},{"tag":"table","caption":null,"complex":true,"data":[[{"colspan":3,"type":"header","content":[{"tag":"text","text":"地质时期"},{"tag":"text","text":"","br":true}]},{"type":"header","content":[{"tag":"text","text":"特征"}]},{"type":"header","content":[{"tag":"text","text":"代"},{"tag":"text","text":"","br":true},{"tag":"text","text":"（界）"},{"tag":"text","text":"","br":true}]},{"type":"header","content":[{"tag":"text","text":"宙"},{"tag":"text","text":"","br":true},{"tag":"text","text":"（宇）"},{"tag":"text","text":"","br":true}]},{"type":"header","content":[{"tag":"text","text":"距今年数"},{"tag":"text","text":"","br":true},{"tag":"text","text":"Ma"},{"tag":"text","text":"","br":true}]}],[{"rowspan":3,"type":"data","content":[{"tag":"text","text":"进"},{"tag":"text","text":"","br":true},{"tag":"text","text":"入"},{"tag":"text","text":"","br":true},{"tag":"innerlink","text":"太","lemmaId":0,"lemmaTitle":"太"},{"tag":"text","text":"","br":true},{"tag":"innerlink","text":"阳","lemmaId":0,"lemmaTitle":"阳"},{"tag":"text","text":"","br":true},{"tag":"innerlink","text":"系","lemmaId":0,"lemmaTitle":"系"},{"tag":"text","text":"","br":true},{"tag":"text","text":"时"},{"tag":"text","text":"","br":true},{"tag":"text","text":"期"},{"tag":"text","text":"","br":true}]},{"rowspan":2,"type":"data","content":[{"tag":"text","text":"地"},{"tag":"text","text":"","br":true},{"tag":"text","text":"月"},{"tag":"text","text":"","br":true},{"tag":"text","text":"系"},{"tag":"text","text":"","br":true},{"tag":"text","text":"形"},{"tag":"text","text":"","br":true},{"tag":"text","text":"成"},{"tag":"text","text":"","br":true},{"tag":"text","text":"时"},{"tag":"text","text":"","br":true},{"tag":"text","text":"期"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"新生"},{"tag":"text","text":"","br":true},{"tag":"text","text":"时"},{"tag":"text","text":"","br":true},{"tag":"text","text":"期"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"这一时期是一颗"},{"tag":"innerlink","text":"彗星","lemmaId":22704,"lemmaTitle":"彗星"},{"tag":"text","text":"撞击地球而开始的。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"这颗彗星在太阳系裂解，形成绕太阳的"},{"tag":"innerlink","text":"小行星带","lemmaId":0,"lemmaTitle":"小行星带"},{"tag":"text","text":"。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"彗星的组成物即有岩石又有冰和大气。在冰里存在着各种生物。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"在这一地质时期，地球增加了水、大气和新的生物物种。原有的生物发生变异或进化。"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"新"},{"tag":"text","text":"","br":true},{"tag":"text","text":"生"},{"tag":"text","text":"","br":true},{"tag":"innerlink","text":"代","lemmaId":16794,"lemmaTitle":"代"},{"tag":"text","text":"","br":true}]},{"rowspan":3,"type":"data","content":[{"tag":"text","text":"","br":true},{"tag":"innerlink","text":"显","lemmaId":0,"lemmaTitle":"显"},{"tag":"text","text":"","br":true},{"tag":"innerlink","text":"生","lemmaId":0,"lemmaTitle":"生"},{"tag":"text","text":"","br":true},{"tag":"innerlink","text":"宙","lemmaId":0,"lemmaTitle":"宙"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"65"}]}],[{"type":"data","content":[]},{"type":"data","content":[{"tag":"text","text":"这一时期是月球被地球俘获形成地月系而开始的。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"月球绕地球转动，使地球的"},{"tag":"innerlink","text":"引力场","lemmaId":0,"lemmaTitle":"引力场"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"磁场","lemmaId":63505,"lemmaTitle":"磁场"},{"tag":"text","text":"发生了变化。在月球引力所形成的晃动作用下，地球的外球发生了旋转，形成地极和磁极的移动。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"在生物界，动物和植物都发生了变异，形成高大的树木和大型的动物。"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"中","lemmaId":0,"lemmaTitle":"中"},{"tag":"text","text":"","br":true},{"tag":"innerlink","text":"生","lemmaId":0,"lemmaTitle":"生"},{"tag":"text","text":"","br":true},{"tag":"innerlink","text":"代","lemmaId":0,"lemmaTitle":"代"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"230"},{"tag":"text","text":"","br":true}]}],[{"colspan":2,"type":"data","content":[]},{"type":"data","content":[{"tag":"text","text":"这一时期是地球进入太阳系成为行星而开始的。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"在这一地质时期，地球有了太阳的光照，形成了绕太阳的公转和自转，有了昼夜的变化。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"在地球的内部，地核或内球偏向太阳引力的反方向，不在地球中心。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"在地壳，由于地球自转形成由两极向赤道的离心力；在太阳引力作用下，由于地球自西向东转动，地壳形成自东向西的运动。形成高山、高原，形成沟谷洼地和平原。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"在生物界，开始爆发式出现即开始复活。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"随着太阳系的演化，地球由进入太阳系时的轨道面即轨道面与太阳"},{"tag":"innerlink","text":"赤道","lemmaId":0,"lemmaTitle":"赤道"},{"tag":"text","text":"面夹角大约23°26\u2032，演化到如今的地球轨道面与太阳赤道面近平行，"},{"tag":"innerlink","text":"地轴","lemmaId":0,"lemmaTitle":"地轴"},{"tag":"text","text":"由垂直轨道面变为倾斜在轨道上运行，形成一年的四季变化。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"在岩石建造上，出现大量的"},{"tag":"innerlink","text":"石灰岩","lemmaId":0,"lemmaTitle":"石灰岩"},{"tag":"text","text":"。"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"古","lemmaId":0,"lemmaTitle":"古"},{"tag":"text","text":"","br":true},{"tag":"innerlink","text":"生","lemmaId":0,"lemmaTitle":"生"},{"tag":"text","text":"","br":true},{"tag":"innerlink","text":"代","lemmaId":0,"lemmaTitle":"代"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"540"},{"tag":"text","text":"","br":true}]}],[{"colspan":3,"type":"data","content":[{"tag":"text","text":"·进入太阳系前时期"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"这一时期是地壳已经形成到地球进入太阳系前的一段地质时间。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"这是一段没有阳光的地质时期。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"在这一段的前期，地壳的风化、剥蚀、搬运和沉积作用强，高山被剥低，在沟谷和坑洼地中沉积了巨厚的原始沉积。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"在这一段的后期，地壳活动变弱，地表温度渐渐降低，到了冰点以下，形成全球性的冰川。"},{"tag":"text","text":"","br":true}]},{"colspan":2,"type":"data","content":[{"tag":"text","text":"","br":true},{"tag":"text","text":"","br":true},{"tag":"text","text":"","br":true},{"tag":"innerlink","text":"元","lemmaId":0,"lemmaTitle":"元"},{"tag":"text","text":"","br":true},{"tag":"innerlink","text":"古","lemmaId":0,"lemmaTitle":"古"},{"tag":"text","text":"","br":true},{"tag":"innerlink","text":"宙","lemmaId":0,"lemmaTitle":"宙"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"2500"},{"tag":"text","text":"","br":true}]}],[{"colspan":3,"type":"data","content":[{"tag":"text","text":"","br":true},{"tag":"text","text":"","br":true},{"tag":"text","text":"","br":true},{"tag":"innerlink","text":"地壳","lemmaId":0,"lemmaTitle":"地壳"},{"tag":"text","text":"形成"},{"tag":"text","text":"","br":true},{"tag":"text","text":"时期"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"这一时期是由地表熔融物质凝固开始到有沉积岩形成的一段地质时间。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"熔融物质凝固形成收缩，在地表形成张裂沟谷高山。宇宙天体撞击，在地表形成大坑洼地。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"随着温度降低，"},{"tag":"innerlink","text":"熔融","lemmaId":0,"lemmaTitle":"熔融"},{"tag":"text","text":"物质凝固过程中产生的水流动汇聚到张裂沟谷和大坑洼地中，产生的气留在地球表面，形成"},{"tag":"innerlink","text":"大气圈","lemmaId":0,"lemmaTitle":"大气圈"},{"tag":"text","text":"。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"地核俘获宇宙物质的不均，地表各处温度高低不均产生大气流动。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"在这一地质时期，地表形成了沟谷高山、大坑洼地，有了水和大气，产生了风化、剥蚀和搬运作用，开始形成沉积岩。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"原始生命蛋白质出现，进化出原核生物（细菌、蓝藻）。"},{"tag":"ref","ctrId":"","index":2,"data":{"type":"3","text":"Springer Shop CP1897.com Paddyfield ShopInHK","index":3,"count":"1"}},{"tag":"text","text":"","br":true}]},{"colspan":2,"type":"data","content":[{"tag":"text","text":"","br":true},{"tag":"text","text":"","br":true},{"tag":"text","text":"","br":true},{"tag":"innerlink","text":"太","lemmaId":0,"lemmaTitle":"太"},{"tag":"text","text":"","br":true},{"tag":"innerlink","text":"古","lemmaId":0,"lemmaTitle":"古"},{"tag":"text","text":"","br":true},{"tag":"innerlink","text":"宙","lemmaId":0,"lemmaTitle":"宙"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"4600"},{"tag":"text","text":"","br":true}]}],[{"colspan":3,"type":"data","content":[{"tag":"text","text":"地球形成"},{"tag":"text","text":"","br":true},{"tag":"text","text":"时期"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"这一时期是由"},{"tag":"innerlink","text":"地核","lemmaId":0,"lemmaTitle":"地核"},{"tag":"text","text":"俘获熔融物质开始到地表熔融物质凝固的一段地质时间。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"在距今约46亿年前，由铁镍物质组成的地核俘获了熔融物质形成"},{"tag":"innerlink","text":"地幔","lemmaId":0,"lemmaTitle":"地幔"},{"tag":"text","text":"。地幔与地核接触部位温度降低，形成内过渡层。地表温度降低凝固，形成外过渡层。"},{"tag":"text","text":"","br":true},{"tag":"text","text":"在这一地质时期，形成了圈层状结构的地球。"},{"tag":"text","text":"","br":true}]},{"colspan":2,"type":"data","content":[{"tag":"text","text":"始"},{"tag":"text","text":"","br":true},{"tag":"text","text":"古"},{"tag":"text","text":"","br":true},{"tag":"text","text":"宙"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":">4600"},{"tag":"text","text":"","br":true}]}]],"length":1096,"lines":59,"pid":"p-17","level":1,"index":"2"},{"tag":"header","number":2,"title":"地理特征","length":4,"lines":1,"pid":"p-18","level":1,"index":"3"},{"tag":"header","number":3,"title":"质量","length":2,"lines":1,"pid":"p-19","level":2,"index":"3-1"},{"tag":"paragraph","content":[{"tag":"text","text":"主词条："},{"tag":"innerlink","text":"地球质量","lemmaId":0,"lemmaTitle":"地球质量"}],"length":8,"lines":1,"pid":"p-20","level":2,"index":"3-1"},{"tag":"paragraph","content":[{"tag":"innerlink","text":"卡文迪许","lemmaId":0,"lemmaTitle":"卡文迪许"},{"tag":"text","text":"认为地球的质量约为5.96×10^24千克，地球的赤道半径ra=6378137m≈6378km，极半径rb=6356752m≈6357km，扁率e=1/298.257，忽略地球非球形对称，平均半径r=6371km。在赤道某海平面处重力加速度的值ga=9.780m/s^2，在北极某海平面处的重力加速度的值gb=9.832m/s^2，全球通用的重力加速度标准值g=9.807m/s^2，地球自转周期为23小时56分4秒（"},{"tag":"innerlink","text":"恒星日","lemmaId":0,"lemmaTitle":"恒星日"},{"tag":"text","text":"），即T=8.616×10^4s。"}],"length":233,"lines":13,"pid":"p-22","float":[{"tag":"module","type":"album","moduleId":2657593,"data":{"basic":{"modulename":"album","desc":"星体","albumTag":"","url":"cover=d8b8c92acbceb57ad52af154&count=5&desc=%E6%98%9F%E4%BD%93","total":5,"coverpic":"d8b8c92acbceb57ad52af154","width":220,"height":165,"style":"float: right;width:220px;height:165px;","layout":"right","moduleId":2657593,"moduleVersionId":279356455,"moduleType":2,"picId":1469093803,"type":{"view":220,"albumview":120,"oriWithWater":221,"litterImage":200,"authModule":150,"view_new":209,"view_card":268},"sizes":{"220":{"url":"https://bkimg.cdn.bcebos.com/pic/6c224f4a20a44623a26000059822720e0cf3d72b?x-bce-process=image/resize,m_lfit,w_220,h_220,limit_1","width":220,"height":165,"size":220},"120":{"url":"https://bkimg.cdn.bcebos.com/pic/6c224f4a20a44623a26000059822720e0cf3d72b?x-bce-process=image/resize,m_lfit,h_120,limit_1","width":160,"height":120,"size":120},"221":{"url":"https://bkimg.cdn.bcebos.com/pic/6c224f4a20a44623a26000059822720e0cf3d72b?x-bce-process=image/watermark,g_7,image_d2F0ZXIvYmFpa2U2MA==,xp_5,yp_5","width":220,"height":165,"size":221},"200":{"url":"https://bkimg.cdn.bcebos.com/pic/6c224f4a20a44623a26000059822720e0cf3d72b?x-bce-process=image/resize,m_lfit,w_200,h_200,limit_1","width":200,"height":150,"size":200},"150":{"url":"https://bkimg.cdn.bcebos.com/pic/6c224f4a20a44623a26000059822720e0cf3d72b?x-bce-process=image/resize,m_lfit,w_150,limit_1","width":150,"height":112,"size":150},"209":{"url":"https://bkimg.cdn.bcebos.com/pic/6c224f4a20a44623a26000059822720e0cf3d72b?x-bce-process=image/resize,m_lfit,w_209,limit_1","width":209,"height":156,"size":209},"268":{"url":"https://bkimg.cdn.bcebos.com/pic/6c224f4a20a44623a26000059822720e0cf3d72b?x-bce-process=image/resize,m_lfit,w_268,limit_1","width":220,"height":165,"size":268}}},"toppics":[{"src":"d8b8c92acbceb57ad52af154","owner":"","desc":"星体","width":800,"height":600,"url":"https://bkimg.cdn.bcebos.com/pic/d8b8c92acbceb57ad52af154?x-bce-process=image/resize,m_fill,w_600,h_600,align_50"},{"src":"1b0d4f0f706e477d6159f354","owner":"窗花阁","desc":"九大星体","height":394,"width":660,"url":"https://bkimg.cdn.bcebos.com/pic/1b0d4f0f706e477d6159f354?x-bce-process=image/resize,m_fill,w_300,h_300,align_50"},{"src":"61183b2d79bdb863359bf754","owner":"窗花阁","desc":"月亮的变化","height":634,"width":640,"url":"https://bkimg.cdn.bcebos.com/pic/61183b2d79bdb863359bf754?x-bce-process=image/resize,m_fill,w_300,h_300,align_50"}],"toppicscount":3},"pid":"p-21"}],"level":2,"index":"3-1"},{"tag":"header","number":3,"title":"温度","length":2,"lines":1,"pid":"p-23","level":2,"index":"3-2"},{"tag":"paragraph","content":[{"tag":"text","text":"地球表面的气温受到太阳辐射的影响，全球地表平均气温约15℃左右。而在不见阳光的地下深处，温度则主要受地热的影响，随深度的增加而增加。在地球中心处的"},{"tag":"innerlink","text":"地核","lemmaId":0,"lemmaTitle":"地核"},{"tag":"text","text":"温度更高达6000℃以上，比太阳光球表面温度（5778K，5500℃）更高。地球表面最热的地方出现在巴士拉，最高气温为58.8℃。地球北半球的\u201c"},{"tag":"innerlink","text":"冷极","lemmaId":0,"lemmaTitle":"冷极"},{"tag":"text","text":"\u201d在"},{"tag":"innerlink","text":"东西伯利亚山地","lemmaId":0,"lemmaTitle":"东西伯利亚山地"},{"tag":"text","text":"的"},{"tag":"innerlink","text":"奥伊米亚康","lemmaId":0,"lemmaTitle":"奥伊米亚康"},{"tag":"text","text":"，1961年1月的最低温度是-71℃。世界的\u201c冷极\u201d在南极大陆，1967年初，俄罗斯人在东方站曾经记录到-89.2℃的最低温度。"}],"length":228,"lines":13,"pid":"p-24","level":2,"index":"3-2"},{"tag":"header","number":3,"title":"电性","length":2,"lines":1,"pid":"p-25","level":2,"index":"3-3"},{"tag":"paragraph","content":[{"tag":"text","text":"因为地球自西向东旋转，而地磁场外部是从磁北极指向磁南极（即南极指向北极），所成的环形电流与地球自转的方向相反，所以是带负电的。"}],"length":63,"lines":4,"pid":"p-26","level":2,"index":"3-3"},{"tag":"header","number":3,"title":"形状","length":2,"lines":1,"pid":"p-27","level":2,"index":"3-4"},{"tag":"paragraph","content":[{"tag":"text","text":"主词条："},{"tag":"innerlink","text":"地球形状","lemmaId":0,"lemmaTitle":"地球形状"}],"length":8,"lines":1,"pid":"p-28","level":2,"index":"3-4"},{"tag":"paragraph","content":[{"tag":"text","text":"月食时，仔细观察就会发现投射在月球上的地球影子总是圆的；往南或往北作长途旅行时，则会发现同一个星星在天空中的高度是不一样的。一些聪明的古人从诸如此类的蛛丝马迹中就已经猜测到地球可能是球形的。托勒密的地心说也明确地描述了地球为球形的观点，但是直到16世纪葡萄牙航海家麦哲伦的船队完成人类历史上的第一次环球航行，才真正用实践无可辩驳地证明了地球是个球体。"}],"length":175,"lines":10,"pid":"p-30","float":[{"tag":"module","type":"album","moduleId":2657594,"data":{"basic":{"modulename":"album","desc":"自然灾害","albumTag":"","url":"cover=7e7f7909408a50813ac76322&count=20&desc=%E8%87%AA%E7%84%B6%E7%81%BE%E5%AE%B3","total":20,"coverpic":"7e7f7909408a50813ac76322","width":220,"height":146,"style":"float: right;width:220px;height:146px;","layout":"right","moduleId":2657594,"moduleVersionId":279356456,"moduleType":2,"picId":1310766985,"type":{"view":220,"albumview":120,"oriWithWater":221,"litterImage":200,"authModule":150,"view_new":209,"view_card":268},"sizes":{"220":{"url":"https://bkimg.cdn.bcebos.com/pic/b2de9c82d158ccbfe536a04419d8bc3eb03541c1?x-bce-process=image/resize,m_lfit,w_220,h_220,limit_1","width":220,"height":146,"size":220},"120":{"url":"https://bkimg.cdn.bcebos.com/pic/b2de9c82d158ccbfe536a04419d8bc3eb03541c1?x-bce-process=image/resize,m_lfit,h_120,limit_1","width":180,"height":120,"size":120},"221":{"url":"https://bkimg.cdn.bcebos.com/pic/b2de9c82d158ccbfe536a04419d8bc3eb03541c1?x-bce-process=image/watermark,g_7,image_d2F0ZXIvYmFpa2U2MA==,xp_5,yp_5","width":220,"height":146,"size":221},"200":{"url":"https://bkimg.cdn.bcebos.com/pic/b2de9c82d158ccbfe536a04419d8bc3eb03541c1?x-bce-process=image/resize,m_lfit,w_200,h_200,limit_1","width":200,"height":132,"size":200},"150":{"url":"https://bkimg.cdn.bcebos.com/pic/b2de9c82d158ccbfe536a04419d8bc3eb03541c1?x-bce-process=image/resize,m_lfit,w_150,limit_1","width":150,"height":99,"size":150},"209":{"url":"https://bkimg.cdn.bcebos.com/pic/b2de9c82d158ccbfe536a04419d8bc3eb03541c1?x-bce-process=image/resize,m_lfit,w_209,limit_1","width":209,"height":138,"size":209},"268":{"url":"https://bkimg.cdn.bcebos.com/pic/b2de9c82d158ccbfe536a04419d8bc3eb03541c1?x-bce-process=image/resize,m_lfit,w_268,limit_1","width":220,"height":146,"size":268}}},"toppics":[{"src":"7e7f7909408a50813ac76322","owner":"","desc":"自然灾害","width":549,"height":366,"url":"https://bkimg.cdn.bcebos.com/pic/7e7f7909408a50813ac76322?x-bce-process=image/resize,m_fill,w_600,h_600,align_50"},{"src":"020e66f035818f8d7831aa23","owner":"战神OX","desc":"龙卷风","height":413,"width":550,"url":"https://bkimg.cdn.bcebos.com/pic/020e66f035818f8d7831aa23?x-bce-process=image/resize,m_fill,w_300,h_300,align_50"},{"src":"f677b1c3bd862a1bb219a823","owner":"战神OX","desc":"龙卷风","height":1333,"width":1000,"url":"https://bkimg.cdn.bcebos.com/pic/f677b1c3bd862a1bb219a823?x-bce-process=image/resize,m_fill,w_300,h_300,align_0"}],"toppicscount":3},"pid":"p-29"}],"level":2,"index":"3-4"},{"tag":"paragraph","content":[{"tag":"text","text":"科学家经过长期的精密测量，发现地球并不是一个"},{"tag":"innerlink","text":"规则","lemmaId":4405,"lemmaTitle":"规则"},{"tag":"text","text":"球体，而是一个两极部位略扁"},{"tag":"innerlink","text":"赤道","lemmaId":0,"lemmaTitle":"赤道"},{"tag":"text","text":"稍鼓的不规则椭圆球体，夸张地说，有点像\u201c梨子\u201d，称之为\u201c梨形体\u201d。地球的赤道半径约长6378.137km，这点差别与地球的平均半径相比，十分微小，从宇宙空间看地球，仍可将它视为一个规则球体。如果按照这个比例制作一个半径为1米的地球仪，那么赤道半径仅仅比极半径长了大约3毫米，凭着人的肉眼是难以察觉出来的，因此在制作地球仪时总是将它做成规则球体。"}],"length":211,"lines":12,"pid":"p-31","level":2,"index":"3-4"},{"tag":"header","number":3,"title":"位置","length":2,"lines":1,"pid":"p-32","level":2,"index":"3-5"},{"tag":"paragraph","content":[{"tag":"text","text":"地球在宇宙中的位置在最近的一个世纪里，这一认识发生了根本性的拓展。起初，地球被认为是宇宙的中心，而当时对宇宙的认识只包括那些肉眼可见的行星和天球上看似固定不变的恒星。17世纪日心说被广泛接受，其后威廉·赫歇尔和其他天文学家通过观测发现太阳位于一个由恒星构成的盘状星系中。到了20世纪，对螺旋状星云的观测显示我们的银河系只是膨胀宇宙中的数十亿计的星系中的一个。到了21世纪，可观测宇宙的整体结构开始变得明朗\u2014\u2014超星系团构成了包含大尺度纤维和空洞的巨大的网状结构。超星系团、大尺度纤维状结构和空洞可能是宇宙中存在的最大的相干结构。在更大的尺度上（十亿秒差距以上）宇宙是均匀的，也就是说其各个部分平均有着相同的密度、组分和结构。"}],"length":311,"lines":18,"pid":"p-33","float":[{"tag":"image","type":"normal","picsrc":"91529822720e0cf337ef25310846f21fbf09aae4","width":1920,"height":240,"title":"宇宙中的地球","layout":"right","moduleId":0,"url":"https://bkimg.cdn.bcebos.com/pic/91529822720e0cf337ef25310846f21fbf09aae4?x-bce-process=image/resize,m_lfit,w_600,h_600,limit_1/quality,q_50"}],"level":2,"index":"3-5"},{"tag":"paragraph","content":[{"tag":"text","text":"宇宙是没有\u201c中心\u201d或者\u201c边界\u201d的，因此我们无法标出地球在整个宇宙中的绝对位置。地球位于可观测宇宙的中心，这是因为可观测性是由到地球的距离决定的。在各种尺度上，我们可以以特定的结构作为参照系来给出地球的相对位置。目前依然无法确定宇宙是否是无穷的。"},{"tag":"ref","ctrId":"","index":3,"data":{"type":"1","title":"环境承载之痛 2050年地球气候将无法逆转","url":"http://www.weather.com.cn/climate/qhbhyw/01/1789172.shtml","site":"中国天气网","publishDate":"","refDate":"2014-07-19","index":4}}],"length":122,"lines":7,"pid":"p-34","level":2,"index":"3-5"},{"tag":"table","caption":null,"complex":false,"data":[[{"type":"header","content":[{"tag":"text","text":"名称"}]},{"type":"header","content":[{"tag":"text","text":"纬线","bold":true}]},{"type":"header","content":[{"tag":"text","text":"经线","bold":true}]}],[{"type":"data","content":[{"tag":"text","text":"定义","bold":true}]},{"type":"data","content":[{"tag":"text","text":"与地轴垂直并且环绕地球一周的圆圈。"}]},{"type":"data","content":[{"tag":"text","text":"连接南北两极并且与纬线垂直相交的半圆。"}]}],[{"type":"data","content":[{"tag":"text","text":"指示方向","bold":true}]},{"type":"data","content":[{"tag":"text","text":"东西方向。"}]},{"type":"data","content":[{"tag":"text","text":"南北方向。"}]}],[{"type":"data","content":[{"tag":"text","text":"长度","bold":true}]},{"type":"data","content":[{"tag":"text","text":"长度不一，赤道最长。"}]},{"type":"data","content":[{"tag":"text","text":"所有经线长度相等。"}]}],[{"type":"data","content":[{"tag":"text","text":"形状","bold":true}]},{"type":"data","content":[{"tag":"text","text":"除极点外，纬线圈都是圆"}]},{"type":"data","content":[{"tag":"text","text":"所有经线都是半圆。"}]}],[{"type":"data","content":[{"tag":"text","text":"起止度数","bold":true},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"0度（0°纬线叫赤道）\u201490°N/S"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"0度（0°经线叫本初子午线）\u2014180°"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"代号","bold":true},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"北纬\u2014N，南纬\u2014S"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"东经\u2014E，西经\u2014W"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"如何区分","bold":true},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"区分南、北纬（两种方法）："},{"tag":"text","text":"","br":true},{"tag":"text","text":"1、赤道（0°纬线）以北为北纬N，赤道以南为南纬S；"},{"tag":"text","text":"","br":true},{"tag":"text","text":"2、纬度向北递增为北纬N，纬度向南递增为南纬S。"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"区分东、西经（两种方法）："},{"tag":"text","text":"","br":true},{"tag":"text","text":"1、本初子午线（0度经线）以东为东经E，本初子午线以西为西经W；"},{"tag":"text","text":"","br":true},{"tag":"text","text":"2、经度向东递增为东经E，经度向西递增为西经E。"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"半球划分","bold":true},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"赤道分南、北半球"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"20°W和160°E分东、西半球"},{"tag":"text","text":"","br":true}]}]],"length":326,"lines":14,"pid":"p-35","level":2,"index":"3-5"},{"tag":"header","number":2,"title":"地形与气候","length":5,"lines":1,"pid":"p-36","level":1,"index":"4"},{"tag":"header","number":3,"title":"气候","length":2,"lines":1,"pid":"p-37","level":2,"index":"4-1"},{"tag":"paragraph","content":[{"tag":"text","text":"主词条："},{"tag":"innerlink","text":"气候","lemmaId":0,"lemmaTitle":"气候"}],"length":6,"lines":1,"pid":"p-38","level":2,"index":"4-1"},{"tag":"paragraph","content":[{"tag":"text","text":"因为地球气候从"},{"tag":"innerlink","text":"亘古","lemmaId":0,"lemmaTitle":"亘古"},{"tag":"text","text":"到现在都有发生巨大变化并且这种变化将继续演进，很难把地球气候概括。地球上与天气和气候有关的自然灾害包括龙卷风、台风、洪水、干旱等。"}],"length":74,"lines":5,"pid":"p-40","float":[{"tag":"module","type":"album","moduleId":19725584,"data":{"basic":{"modulename":"album","desc":"地球的气候","albumTag":"","url":"cover=a044ad345982b2b7828d1a4b36adcbef76099b2a&count=3&desc=%E5%9C%B0%E7%90%83%E7%9A%84%E6%B0%94%E5%80%99","total":3,"coverpic":"a044ad345982b2b7828d1a4b36adcbef76099b2a","width":220,"height":158,"style":"float: right;width:220px;height:158px;","layout":"right","moduleId":19725584,"moduleVersionId":279356460,"moduleType":2,"picId":33270673234,"type":{"view":220,"albumview":120,"oriWithWater":221,"litterImage":200,"authModule":150,"view_new":209,"view_card":268},"sizes":{"220":{"url":"https://bkimg.cdn.bcebos.com/pic/a044ad345982b2b7828d1a4b36adcbef76099b2a?x-bce-process=image/resize,m_lfit,w_220,h_220,limit_1","width":220,"height":158,"size":220},"120":{"url":"https://bkimg.cdn.bcebos.com/pic/a044ad345982b2b7828d1a4b36adcbef76099b2a?x-bce-process=image/resize,m_lfit,h_120,limit_1","width":167,"height":120,"size":120},"221":{"url":"https://bkimg.cdn.bcebos.com/pic/a044ad345982b2b7828d1a4b36adcbef76099b2a?x-bce-process=image/watermark,g_7,image_d2F0ZXIvYmFpa2U2MA==,xp_5,yp_5","width":220,"height":158,"size":221},"200":{"url":"https://bkimg.cdn.bcebos.com/pic/a044ad345982b2b7828d1a4b36adcbef76099b2a?x-bce-process=image/resize,m_lfit,w_200,h_200,limit_1","width":200,"height":143,"size":200},"150":{"url":"https://bkimg.cdn.bcebos.com/pic/a044ad345982b2b7828d1a4b36adcbef76099b2a?x-bce-process=image/resize,m_lfit,w_150,limit_1","width":150,"height":107,"size":150},"209":{"url":"https://bkimg.cdn.bcebos.com/pic/a044ad345982b2b7828d1a4b36adcbef76099b2a?x-bce-process=image/resize,m_lfit,w_209,limit_1","width":209,"height":150,"size":209},"268":{"url":"https://bkimg.cdn.bcebos.com/pic/a044ad345982b2b7828d1a4b36adcbef76099b2a?x-bce-process=image/resize,m_lfit,w_268,limit_1","width":220,"height":158,"size":268}}},"toppics":[{"src":"a044ad345982b2b7828d1a4b36adcbef76099b2a","owner":"","desc":"地球的气候","width":619,"height":447,"url":"https://bkimg.cdn.bcebos.com/pic/a044ad345982b2b7828d1a4b36adcbef76099b2a?x-bce-process=image/resize,m_fill,w_600,h_600,align_50"},{"src":"71cf3bc79f3df8dc77d4af1eca11728b46102857","owner":"沈局延吉段","desc":"五带的划分","height":504,"width":618,"url":"https://bkimg.cdn.bcebos.com/pic/71cf3bc79f3df8dc77d4af1eca11728b46102857?x-bce-process=image/resize,m_fill,w_300,h_300,align_50"},{"src":"42166d224f4a20a45b0c1f4897529822720ed02a","owner":"沈局延吉段","desc":"世界各地的气候类型","height":572,"width":844,"url":"https://bkimg.cdn.bcebos.com/pic/42166d224f4a20a45b0c1f4897529822720ed02a?x-bce-process=image/resize,m_fill,w_300,h_300,align_50"}],"toppicscount":3},"pid":"p-39"}],"level":2,"index":"4-1"},{"tag":"paragraph","content":[{"tag":"text","text":"两极地气候被两个温度相差并非很大的区域分隔开来："},{"tag":"innerlink","text":"赤道","lemmaId":0,"lemmaTitle":"赤道"},{"tag":"text","text":"附近宽广的热带气候和稍高纬度上的亚热带气候，降水模式在不同地区也差异巨大，降水量从一年几米到一年少于一毫米的地区都有。"},{"tag":"ref","ctrId":"","index":4,"data":{"type":"1","title":"地球","url":"http://news.xinhuanet.com/ziliao/2006-08/21/content_4988775.htm","site":"新华网","publishDate":"","refDate":"2014-07-19","index":5}}],"length":85,"lines":5,"pid":"p-41","level":2,"index":"4-1"},{"tag":"header","number":3,"title":"地貌","length":2,"lines":1,"pid":"p-42","level":2,"index":"4-2"},{"tag":"paragraph","content":[{"tag":"text","text":"海陆分布","bold":true}],"length":4,"lines":1,"pid":"p-43","level":2,"index":"4-2"},{"tag":"paragraph","content":[{"tag":"text","text":"地球总面积约为5.10072亿平方千米，其中约29.2%（1.4894亿平方千米）是陆地，其余70.8%（3.61132亿平方千米）是水。陆地主要在北半球，有五个大陆："},{"tag":"innerlink","text":"欧亚大陆","lemmaId":0,"lemmaTitle":"欧亚大陆"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"非洲大陆","lemmaId":0,"lemmaTitle":"非洲大陆"},{"tag":"text","text":"、美洲大陆、"},{"tag":"innerlink","text":"澳大利亚大陆","lemmaId":0,"lemmaTitle":"澳大利亚大陆"},{"tag":"text","text":"和"},{"tag":"innerlink","text":"南极大陆","lemmaId":24848,"lemmaTitle":"南极大陆"},{"tag":"text","text":"，另个还有很多岛屿。大洋则包括"},{"tag":"innerlink","text":"太平洋","lemmaId":0,"lemmaTitle":"太平洋"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"大西洋","lemmaId":10883,"lemmaTitle":"大西洋"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"印度洋","lemmaId":0,"lemmaTitle":"印度洋"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"北冰洋","lemmaId":0,"lemmaTitle":"北冰洋"},{"tag":"text","text":"和"},{"tag":"innerlink","text":"南冰洋","lemmaId":0,"lemmaTitle":"南冰洋"},{"tag":"text","text":"五个大洋及其附属海域。海岸线共35.6万千米。"}],"length":167,"lines":10,"pid":"p-44","level":2,"index":"4-2"},{"tag":"paragraph","content":[{"tag":"text","text":"极端海拔","bold":true}],"length":4,"lines":1,"pid":"p-45","level":2,"index":"4-2"},{"tag":"paragraph","content":[{"tag":"text","text":"陆地上最低点："},{"tag":"innerlink","text":"死海","lemmaId":509,"lemmaTitle":"死海"},{"tag":"text","text":"（-418米）"}],"length":16,"lines":1,"pid":"p-46","level":2,"index":"4-2"},{"tag":"paragraph","content":[{"tag":"text","text":"全球最低点："},{"tag":"innerlink","text":"马里亚纳海沟","lemmaId":172541,"lemmaTitle":"马里亚纳海沟"},{"tag":"text","text":"（-11034米）"}],"length":21,"lines":2,"pid":"p-47","level":2,"index":"4-2"},{"tag":"paragraph","content":[{"tag":"text","text":"全球最高点："},{"tag":"innerlink","text":"珠穆朗玛峰","lemmaId":3058,"lemmaTitle":"珠穆朗玛峰"},{"tag":"text","text":"（8844.43米）"}],"length":21,"lines":2,"pid":"p-48","level":2,"index":"4-2"},{"tag":"header","number":2,"title":"人文地理","length":4,"lines":1,"pid":"p-49","level":1,"index":"5"},{"tag":"paragraph","content":[{"tag":"text","text":"主词条："},{"tag":"innerlink","text":"世界","lemmaId":24458,"lemmaTitle":"世界"}],"length":6,"lines":1,"pid":"p-50","level":1,"index":"5"},{"tag":"header","number":3,"title":"人口","length":2,"lines":1,"pid":"p-51","level":2,"index":"5-1"},{"tag":"paragraph","content":[{"tag":"innerlink","text":"世界人口","lemmaId":0,"lemmaTitle":"世界人口"},{"tag":"text","text":"总数是人类在一个特定的时间内在地球上生活的数目。根据"},{"tag":"innerlink","text":"美国人口调查局","lemmaId":0,"lemmaTitle":"美国人口调查局"},{"tag":"text","text":"的估计，截至2013年1月4日，全世界约有70.58亿人。世界人口在15世纪的"},{"tag":"innerlink","text":"黑死病","lemmaId":0,"lemmaTitle":"黑死病"},{"tag":"text","text":"后不断增长，最快的世界人口增长率（高于1.8%）出现于20世纪50年代。根据世界人口预测，世界人口将继续增长直到2050年。"}],"length":141,"lines":8,"pid":"p-52","level":2,"index":"5-1"},{"tag":"header","number":3,"title":"政区","length":2,"lines":1,"pid":"p-53","level":2,"index":"5-2"},{"tag":"paragraph","content":[{"tag":"text","text":"世界上共有226个国家和地区，国家199个，地区27个。亚洲（48个国家），欧洲（44个国家/2个地区），非洲（53个国家/3个地区），大洋洲（14个国家/10个地区），北美洲（23个国家/13个地区），南美洲（12个国家/1个地区）。"}],"length":118,"lines":7,"pid":"p-54","level":2,"index":"5-2"},{"tag":"header","number":2,"title":"结构","length":2,"lines":1,"pid":"p-55","level":1,"index":"6"},{"tag":"header","number":3,"title":"综述","length":2,"lines":1,"pid":"p-56","level":2,"index":"6-1"},{"tag":"paragraph","content":[{"tag":"text","text":"主词条："},{"tag":"innerlink","text":"地球圈层","lemmaId":0,"lemmaTitle":"地球圈层"}],"length":8,"lines":1,"pid":"p-57","level":2,"index":"6-1"},{"tag":"paragraph","content":[{"tag":"text","text":"地球圈层分为地球外圈和地球内圈两大部分。地球外圈可进一步划分为四个基本圈层，即大气圈、水圈、生物圈和岩石圈；地球内圈可进一步划分为三个基本圈层，即地幔圈、外核液体圈和固体内核圈。此外在地球外圈和地球内圈之间还存在一个软流圈，它是地球外圈与地球内圈之间的一个过渡圈层，位于地面以下平均深度约150公里处。这样，整个地球总共包括八个圈层，其中岩石圈、软流圈和地球内圈一起构成了所谓的固体地球。对于地球外圈中的大气圈、水圈和生物圈，以及岩石圈的表面，一般用直接观测和测量的方法进行研究。而地球内圈，主要用地球物理的方法，例如地震学、重力学和高精度现代空间测地技术观测的反演等进行研究。地球各圈层在分布上有一个显著的特点，即固体地球内部与表面之上的高空基本上是上下平行分布的，而在地球表面附近，各圈层则是相互渗透甚至相互重叠的，其中生物圈表现最为显著，其次是水圈。"}],"length":378,"lines":21,"pid":"p-58","level":2,"index":"6-1"},{"tag":"table","caption":"固体地球结构表","complex":true,"data":[[{"colspan":4,"type":"data","content":[{"tag":"text","text":"地球圈层名称"},{"tag":"text","text":"","br":true}]},{"rowspan":2,"type":"data","content":[{"tag":"text","text":"深度"},{"tag":"text","text":"","br":true},{"tag":"text","text":"（公里）"},{"tag":"text","text":"","br":true}]},{"rowspan":2,"type":"data","content":[{"tag":"text","text":"地震"},{"tag":"text","text":"","br":true},{"tag":"text","text":"纵波速度"},{"tag":"text","text":"","br":true},{"tag":"text","text":"（公里/秒）"},{"tag":"text","text":"","br":true}]},{"rowspan":2,"type":"data","content":[{"tag":"text","text":"地震"},{"tag":"text","text":"","br":true},{"tag":"text","text":"横波速度"},{"tag":"text","text":"","br":true},{"tag":"text","text":"（公里/秒）"},{"tag":"text","text":"","br":true}]},{"rowspan":2,"type":"data","content":[{"tag":"text","text":"密度（克/立方厘米）"},{"tag":"text","text":"","br":true}]},{"rowspan":2,"type":"data","content":[{"tag":"text","text":"物质"},{"tag":"text","text":"","br":true},{"tag":"text","text":"状态"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"一级"},{"tag":"text","text":"","br":true},{"tag":"text","text":"分层"},{"tag":"text","text":"","br":true}]},{"colspan":2,"type":"data","content":[{"tag":"text","text":"二级"},{"tag":"text","text":"","br":true},{"tag":"text","text":"分层"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"传统"},{"tag":"text","text":"","br":true},{"tag":"text","text":"分层"},{"tag":"text","text":"","br":true}]}],[{"rowspan":3,"type":"data","content":[{"tag":"text","text":"外"},{"tag":"text","text":"","br":true},{"tag":"text","text":"球"},{"tag":"text","text":"","br":true}]},{"colspan":2,"type":"data","content":[{"tag":"text","text":"地壳"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"地壳","lemmaId":1184613,"lemmaTitle":"地壳"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"0-33"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"5.6-7.0"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"3.4-4.2"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"2.6-2.9"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"固态物质"},{"tag":"text","text":"","br":true}]}],[{"rowspan":2,"type":"data","content":[{"tag":"text","text":"外"},{"tag":"text","text":"","br":true},{"tag":"text","text":"过"},{"tag":"text","text":"","br":true},{"tag":"text","text":"渡"},{"tag":"text","text":"","br":true},{"tag":"text","text":"层"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"外过渡层"},{"tag":"text","text":"","br":true},{"tag":"text","text":"（上）"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"上地幔","lemmaId":0,"lemmaTitle":"上地幔"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"33-980"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"8.1-10.1"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"4.4-5.4"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"3.2-3.6"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"部分"},{"tag":"text","text":"","br":true},{"tag":"text","text":"熔融物质"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"外过渡层"},{"tag":"text","text":"","br":true},{"tag":"text","text":"（下）"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"下地幔","lemmaId":0,"lemmaTitle":"下地幔"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"980-2900"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"12.8-13.5"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"6.9-7.2"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"5.1-5.6"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"液态\u2014固态物质"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"液"},{"tag":"text","text":"","br":true},{"tag":"text","text":"态"},{"tag":"text","text":"","br":true},{"tag":"text","text":"层"},{"tag":"text","text":"","br":true}]},{"colspan":2,"type":"data","content":[{"tag":"text","text":"液态层"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"外地核","lemmaId":0,"lemmaTitle":"外地核"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"2900-4700"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"8.0-8.2"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"不能通过"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"10.0-11.4"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"液态物质"},{"tag":"text","text":"","br":true}]}],[{"rowspan":2,"type":"data","content":[{"tag":"text","text":"内"},{"tag":"text","text":"","br":true},{"tag":"text","text":"球"},{"tag":"text","text":"","br":true}]},{"colspan":2,"type":"data","content":[{"tag":"text","text":"内过"},{"tag":"text","text":"","br":true},{"tag":"text","text":"度层"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"过度层"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"4700-5100"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"9.5-10.3"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[]},{"type":"data","content":[{"tag":"text","text":"12.3"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"液态\u2014固态物质"},{"tag":"text","text":"","br":true}]}],[{"colspan":2,"type":"data","content":[{"tag":"text","text":"地核"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"地核","lemmaId":0,"lemmaTitle":"地核"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"5100-6371"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"10.9-11.2"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[]},{"type":"data","content":[{"tag":"text","text":"12.5"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"固态物质"},{"tag":"text","text":"","br":true}]}]],"length":302,"lines":8,"pid":"p-59","level":2,"index":"6-1"},{"tag":"header","number":3,"title":"大气圈","length":3,"lines":1,"pid":"p-60","level":2,"index":"6-2"},{"tag":"paragraph","content":[{"tag":"text","text":"主词条："},{"tag":"innerlink","text":"大气圈","lemmaId":0,"lemmaTitle":"大气圈"}],"length":7,"lines":1,"pid":"p-61","level":2,"index":"6-2"},{"tag":"paragraph","content":[{"tag":"text","text":"地球大气圈是地球外圈中最外部的气体圈层，它包围着海洋和陆地。大气圈没有确切的上界，在2000～1.6万公里高空仍有稀薄的气体和基本粒子。在地下，土壤和某些岩石中也会有少量空气，它们也可认为是大气圈的一个组成部分。地球大气的主要成分为"},{"tag":"innerlink","text":"氮","lemmaId":0,"lemmaTitle":"氮"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"氧","lemmaId":83765,"lemmaTitle":"氧"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"氩","lemmaId":0,"lemmaTitle":"氩"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"二氧化碳","lemmaId":0,"lemmaTitle":"二氧化碳"},{"tag":"text","text":"和不到0.04%比例的微量气体。地球大气圈气体的总质量约为5.136×10"},{"tag":"text","text":"21","sup":true},{"tag":"text","text":"克，相当于地球总质量的0.86%。由于"},{"tag":"innerlink","text":"地心引力","lemmaId":0,"lemmaTitle":"地心引力"},{"tag":"text","text":"作用，几乎全部的气体集中在离地面100公里的高度范围内，其中75%的大气又集中在地面至10公里高度的"},{"tag":"innerlink","text":"对流层","lemmaId":0,"lemmaTitle":"对流层"},{"tag":"text","text":"范围内。根据大气分布特征，在对流层之上还可分为"},{"tag":"innerlink","text":"平流层","lemmaId":0,"lemmaTitle":"平流层"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"中间层","lemmaId":4863525,"lemmaTitle":"中间层"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"热成层","lemmaId":0,"lemmaTitle":"热成层"},{"tag":"text","text":"等。"}],"length":277,"lines":16,"pid":"p-62","level":2,"index":"6-2"},{"tag":"header","number":3,"title":"水圈","length":2,"lines":1,"pid":"p-63","level":2,"index":"6-3"},{"tag":"paragraph","content":[{"tag":"text","text":"主词条："},{"tag":"innerlink","text":"水圈","lemmaId":0,"lemmaTitle":"水圈"}],"length":6,"lines":1,"pid":"p-64","level":2,"index":"6-3"},{"tag":"paragraph","content":[{"tag":"text","text":"水圈包括海洋、江河、湖泊、"},{"tag":"innerlink","text":"沼泽","lemmaId":359178,"lemmaTitle":"沼泽"},{"tag":"text","text":"、冰川和地下水等，它是一个连续但不很规则的圈层。从离地球数万公里的高空看地球，可以看到地球大气圈中水汽形成的白云和覆盖地球大部分的蓝色海洋，它使地球成为一颗\"蓝色的行星\"。地球水圈总质量为1.66×10"},{"tag":"text","text":"24","sup":true},{"tag":"text","text":"g，约为地球总质量的1/3600，其中海洋水质量约为陆地（包括河流、湖泊和表层岩石孔隙和土壤中）水的35倍。如果整个地球没有固体部分的起伏，那么全球将被深达2600米的水层所均匀覆盖。大气圈和水圈相结合，组成地表的"},{"tag":"innerlink","text":"流体","lemmaId":0,"lemmaTitle":"流体"},{"tag":"text","text":"系统。"}],"length":230,"lines":13,"pid":"p-65","level":2,"index":"6-3"},{"tag":"header","number":3,"title":"生物圈","length":3,"lines":1,"pid":"p-66","level":2,"index":"6-4"},{"tag":"paragraph","content":[{"tag":"text","text":"主词条："},{"tag":"innerlink","text":"生物圈","lemmaId":0,"lemmaTitle":"生物圈"}],"length":7,"lines":1,"pid":"p-67","level":2,"index":"6-4"},{"tag":"paragraph","content":[{"tag":"text","text":"由于存在地球大气圈、地球水圈和地表的矿物，在地球上这个合适的温度条件下，形成了适合于生物生存的"},{"tag":"innerlink","text":"自然","lemmaId":0,"lemmaTitle":"自然"},{"tag":"text","text":"环境。人们通常所说的生物，是指有生命的物体，包括"},{"tag":"innerlink","text":"植物","lemmaId":0,"lemmaTitle":"植物"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"动物","lemmaId":0,"lemmaTitle":"动物"},{"tag":"text","text":"和微生物。据估计，现有生存的"},{"tag":"innerlink","text":"植物","lemmaId":0,"lemmaTitle":"植物"},{"tag":"text","text":"约有40万种，"},{"tag":"innerlink","text":"动物","lemmaId":0,"lemmaTitle":"动物"},{"tag":"text","text":"约有110多万种，微生物至少有10多万种。据统计，在地质"},{"tag":"innerlink","text":"历史","lemmaId":0,"lemmaTitle":"历史"},{"tag":"text","text":"上曾生存过的生物约有5亿～10亿种之多，然而，在地球漫长的演化过程中，绝大部分都已经灭绝了。现存的生物生活在岩石圈的上层部分、大气圈的下层部分和水圈的全部，构成了地球上一个独特的圈层，称为生物圈。生物圈是太阳系所有行星中仅在地球上存在的一个独特圈层。"}],"length":258,"lines":15,"pid":"p-68","level":2,"index":"6-4"},{"tag":"header","number":3,"title":"岩石圈","length":3,"lines":1,"pid":"p-69","level":2,"index":"6-5"},{"tag":"paragraph","content":[{"tag":"text","text":"主词条："},{"tag":"innerlink","text":"岩石圈","lemmaId":0,"lemmaTitle":"岩石圈"}],"length":7,"lines":1,"pid":"p-70","level":2,"index":"6-5"},{"tag":"paragraph","content":[{"tag":"text","text":"对于地球岩石圈，除表面形态外，是无法直接观测到的。它主要由地球的地壳和地幔圈中上地幔的顶部组成，从固体地球表面向下穿过"},{"tag":"innerlink","text":"地震","lemmaId":0,"lemmaTitle":"地震"},{"tag":"text","text":"波在近33公里处所显示的第一个不连续面（"},{"tag":"innerlink","text":"莫霍面","lemmaId":0,"lemmaTitle":"莫霍面"},{"tag":"text","text":"），一直延伸到软流圈为止。岩石圈厚度不均一，平均厚度约为100公里。由于岩石圈及其表面形态与现代地球物理学、地球动力学有着密切的关系，因此，岩石圈是现代地球科学中研究得最多、最详细、最彻底的固体地球部分。由于洋底占据了地球表面总面积的2/3之多，而大洋盆地约占海底总面积的45%，其平均水深为4000～5000米，大量发育的海底火山就是分布在大洋盆地中，其周围延伸着广阔的海底丘陵。因此，整个固体地球的主要表面形态可认为是由大洋盆地与大陆台地组成，对它们的研究，构成了与岩石圈构造和地球动力学有直接联系的\"全球构造学\"理论。"}],"length":346,"lines":20,"pid":"p-71","level":2,"index":"6-5"},{"tag":"header","number":3,"title":"软流圈","length":3,"lines":1,"pid":"p-72","level":2,"index":"6-6"},{"tag":"paragraph","content":[{"tag":"text","text":"主词条："},{"tag":"innerlink","text":"软流圈","lemmaId":0,"lemmaTitle":"软流圈"}],"length":7,"lines":1,"pid":"p-73","level":2,"index":"6-6"},{"tag":"paragraph","content":[{"tag":"text","text":"在距地球表面以下约100公里的上地幔中，有一个明显的"},{"tag":"innerlink","text":"地震","lemmaId":0,"lemmaTitle":"地震"},{"tag":"text","text":"波的低速层，这是由古登堡在1926年最早提出的，称之为软流圈，它位于上地幔的上部即B层。在洋底下面，它位于约60公里深度以下；在大陆地区，它位于约120公里深度以下，平均深度约位于60～250公里处。现代观测和研究已经肯定了这个软流圈层的存在。也就是由于这个"},{"tag":"innerlink","text":"软流圈","lemmaId":0,"lemmaTitle":"软流圈"},{"tag":"text","text":"的存在，将地球外圈与地球内圈区别开来了。"}],"length":180,"lines":10,"pid":"p-74","level":2,"index":"6-6"},{"tag":"header","number":3,"title":"地幔圈","length":3,"lines":1,"pid":"p-75","level":2,"index":"6-7"},{"tag":"paragraph","content":[{"tag":"text","text":"主词条："},{"tag":"innerlink","text":"地幔圈","lemmaId":0,"lemmaTitle":"地幔圈"}],"length":7,"lines":1,"pid":"p-76","level":2,"index":"6-7"},{"tag":"paragraph","content":[{"tag":"innerlink","text":"地震","lemmaId":0,"lemmaTitle":"地震"},{"tag":"text","text":"波除了在地面以下约33公里处有一个显著的不连续面（称为莫霍面）之外，在软流圈之下，直至地球内部约2900公里深度的界面处，属于地幔圈。由于地球外核为液态，在地幔中的"},{"tag":"innerlink","text":"地震","lemmaId":0,"lemmaTitle":"地震"},{"tag":"text","text":"波S波不能穿过此界面在外核中传播。P波曲线在此界面处的速度也急剧减低。这个界面是古登堡在1914年发现的，所以也称为古登堡面，它构成了地幔圈与外核流体圈的分界面。整个地幔圈由上地幔（33～410公里）、下地幔的D\u2032层（1000～2700公里深度）和下地幔的D\u2033层（2700～2900公里深度）组成。地球物理的研究表明，D\u2032层存在强烈的横向不均匀性，其不均匀的程度甚至可以和岩石层相比拟，它不仅是地核热量传送到地幔的热边界层，而且极可能是与地幔有不同化学成分的化学分层。"}],"length":320,"lines":18,"pid":"p-77","level":2,"index":"6-7"},{"tag":"header","number":3,"title":"外核液体圈","length":5,"lines":1,"pid":"p-78","level":2,"index":"6-8"},{"tag":"paragraph","content":[{"tag":"text","text":"主词条："},{"tag":"innerlink","text":"外核液体圈","lemmaId":0,"lemmaTitle":"外核液体圈"}],"length":9,"lines":1,"pid":"p-79","level":2,"index":"6-8"},{"tag":"paragraph","content":[{"tag":"text","text":"地幔圈之下就是所谓的外核液体圈，它位于地面以下约2900～5120公里深度。整个外核液体圈基本上可能是由动力学黏度很小的液体构成的，其中2900至4980公里深度称为"},{"tag":"innerlink","text":"E层","lemmaId":0,"lemmaTitle":"E层"},{"tag":"text","text":"，完全由液体构成。4980～5120公里深度层称为"},{"tag":"innerlink","text":"F层","lemmaId":0,"lemmaTitle":"F层"},{"tag":"text","text":"，它是外核液体圈与固体内核圈之间一个很薄的过渡层。"}],"length":137,"lines":8,"pid":"p-80","level":2,"index":"6-8"},{"tag":"header","number":3,"title":"固体内核圈","length":5,"lines":1,"pid":"p-81","level":2,"index":"6-9"},{"tag":"paragraph","content":[{"tag":"text","text":"主词条："},{"tag":"innerlink","text":"固体内核圈","lemmaId":0,"lemmaTitle":"固体内核圈"}],"length":9,"lines":1,"pid":"p-82","level":2,"index":"6-9"},{"tag":"paragraph","content":[{"tag":"text","text":"地球八个圈层中最靠近地心的就是所谓的固体内核圈了，它位于5120～6371公里地心处，又称为G层。根据对"},{"tag":"innerlink","text":"地震","lemmaId":0,"lemmaTitle":"地震"},{"tag":"text","text":"波速的探测与研究，证明G层为固体结构。地球内层不是均质的，平均地球密度为5.515克/立方厘米，而地球岩石圈的密度仅为2.6～3.0克/立方厘米。由此，地球内部的密度必定要大得多，并随深度的增加，密度也出现明显的变化。地球内部的温度随深度而上升。根据最近的估计，在100公里深度处温度为1300℃，300公里处为2000℃，在地幔圈与外核液态圈边界处，约为4000℃，地心处温度则在6000℃以上。"}],"length":253,"lines":15,"pid":"p-83","level":2,"index":"6-9"},{"tag":"header","number":2,"title":"运动","length":2,"lines":1,"pid":"p-84","level":1,"index":"7"},{"tag":"header","number":3,"title":"自转","length":2,"lines":1,"pid":"p-85","level":2,"index":"7-1"},{"tag":"paragraph","content":[{"tag":"text","text":"主词条："},{"tag":"innerlink","text":"地球自转","lemmaId":0,"lemmaTitle":"地球自转"}],"length":8,"lines":1,"pid":"p-86","level":2,"index":"7-1"},{"tag":"paragraph","content":[{"tag":"text","text":"地球存在绕自转轴自西向东的自转，平均"},{"tag":"innerlink","text":"角速度","lemmaId":0,"lemmaTitle":"角速度"},{"tag":"text","text":"为每小时转动15度。在地球赤道上，自转的"},{"tag":"innerlink","text":"线速度","lemmaId":0,"lemmaTitle":"线速度"},{"tag":"text","text":"是每秒465米。天空中各种天体东升西落的现象都是地球自转的反映。人们最早利用地球自转作为计量时间的基准。自20世纪以来由于"},{"tag":"innerlink","text":"天文观测","lemmaId":0,"lemmaTitle":"天文观测"},{"tag":"text","text":"技术的发展，人们发现地球自转是不均的。1967年国际上开始建立比地球自转更为精确和稳定的原子时。由于原子时的建立和采用，地球自转中的各种变化相继被发现。天文学家已经知道地球自转速度存在长期减慢、不规则变化和"},{"tag":"innerlink","text":"周期性","lemmaId":0,"lemmaTitle":"周期性"},{"tag":"text","text":"变化。"}],"length":218,"lines":13,"pid":"p-87","level":2,"index":"7-1"},{"tag":"paragraph","content":[{"tag":"text","text":"地球自转的周期性变化主要包括周年周期的变化，月周期、半月周期变化以及近周日和半周日周期的变化。周年周期变化，也称为季节性变化，是20世纪30年代发现的，它表现为春天地球自转变慢，秋天地球自转加快，其中还带有半年周期的变化。周年变化的"},{"tag":"innerlink","text":"振幅","lemmaId":548505,"lemmaTitle":"振幅"},{"tag":"text","text":"为20～25毫秒，主要由风的"},{"tag":"innerlink","text":"季节性","lemmaId":0,"lemmaTitle":"季节性"},{"tag":"text","text":"变化引起。半年变化的振幅为8～9毫秒，主要由太阳"},{"tag":"innerlink","text":"潮汐","lemmaId":82338,"lemmaTitle":"潮汐"},{"tag":"text","text":"作用引起的。此外，月周期和半月周期变化的振幅约为±1毫秒，是由月亮潮汐力引起的。地球自转具有周日和半周日变化是在最近的十年中才被发现并得到证实的，振幅只有约0.1毫秒，主要是由月亮的周日、半周日"},{"tag":"innerlink","text":"潮汐","lemmaId":82338,"lemmaTitle":"潮汐"},{"tag":"text","text":"作用引起的。"},{"tag":"ref","ctrId":"","index":5,"data":{"type":"1","title":"月亮是地球唯一的","url":"http://www.xue163.com/18880/140402/1404020078504378.html","site":"中国学网","publishDate":"","refDate":"2014-07-19","index":6}}],"length":266,"lines":15,"pid":"p-88","level":2,"index":"7-1"},{"tag":"header","number":3,"title":"公转","length":2,"lines":1,"pid":"p-89","level":2,"index":"7-2"},{"tag":"paragraph","content":[{"tag":"text","text":"主词条："},{"tag":"innerlink","text":"地球公转","lemmaId":0,"lemmaTitle":"地球公转"}],"length":8,"lines":1,"pid":"p-90","level":2,"index":"7-2"},{"tag":"paragraph","content":[{"tag":"text","text":"地球公转的轨道是椭圆的，公转轨道半长径为149597870公里，轨道的偏心率为0.0167，公转的平均轨道速度为每秒29.79公里；公转的轨道面（黄道面）与地球赤道面的交角为23°27'，称为黄赤交角。地球自转产生了地球上的昼夜变化，地球公转及黄赤交角的存在造成了四季的交替。"}],"length":138,"lines":8,"pid":"p-92","float":[{"tag":"module","type":"video","moduleId":2657600,"data":{"basic":{"modulename":"video","moduleId":2657600,"moduleVersionId":279356454,"moduleType":5},"content":{"partner":"tudou_v","style":"float: right;","layout":"right","id":"xSIMn6uK1fw","title":"来自地球轨道的鬼光","desc":"从国际空间站俯瞰地球","time":"245"}},"pid":"p-91"}],"level":2,"index":"7-2"},{"tag":"paragraph","content":[{"tag":"text","text":"从地球上看，太阳沿黄道逆时针运动，黄道和赤道在天球上存在相距180°的两个交点，其中太阳沿黄道从天赤道以南向北通过天赤道的那一点，称为春分点，与春分点相隔180°的另一点，称为秋分点，太阳分别在每年的春分（3月21日前后）和秋分（9月23日前后）通过春分点和秋分点。对居住的北半球的人来说，当太阳分别经过春分点和秋分点时，就意味着已是春季或是秋季时节。太阳通过春分点到达最北的那一点称为夏至点，与之相差180°的另一点称为冬至点，太阳分别于每年的6月22日前后和12月22日前后通过夏至点和冬至点。同样，对居住在北半球的人，当太阳在夏至点和冬至点附近，从天文学意义上，已进入夏季和冬季时节。上述情况，对于居住在南半球的人，则正好相反。"},{"tag":"ref","ctrId":"","index":5,"data":{"type":"1","title":"月亮是地球唯一的","url":"http://www.xue163.com/18880/140402/1404020078504378.html","site":"中国学网","publishDate":"","refDate":"2014-07-19","index":6}}],"length":317,"lines":18,"pid":"p-93","level":2,"index":"7-2"},{"tag":"header","number":2,"title":"时代划分","length":4,"lines":1,"pid":"p-94","level":1,"index":"8"},{"tag":"table","caption":null,"complex":false,"data":[[{"type":"header","content":[{"tag":"text","text":"序号","bold":true}]},{"type":"header","content":[{"tag":"text","text":"史前时代","bold":true}]},{"type":"header","content":[{"tag":"text","text":"距今","bold":true},{"tag":"text","text":"","br":true},{"tag":"text","text":"单位：亿年","bold":true},{"tag":"text","text":"","br":true}]},{"type":"header","content":[{"tag":"text","text":"主要事件","bold":true}]}],[{"type":"data","content":[{"tag":"text","text":"1"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"冥古宙","lemmaId":0,"lemmaTitle":"冥古宙"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"隐生代","lemmaId":0,"lemmaTitle":"隐生代"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"45.7"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"地球出现"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"2"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"原生代","lemmaId":0,"lemmaTitle":"原生代"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"41.5"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"地球上出现第一个生物\u2014\u2014细菌"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"3"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"酒神代","lemmaId":0,"lemmaTitle":"酒神代"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"39.5"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"古细菌出现"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"4"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"早雨海代","lemmaId":0,"lemmaTitle":"早雨海代"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"38.5"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"地球上出现海洋和其他的水"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"5"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"太古宙","lemmaId":0,"lemmaTitle":"太古宙"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"始太古代","lemmaId":0,"lemmaTitle":"始太古代"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"38"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"地球的岩石圈、水圈、大气圈和生命形成"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"6"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"古太古代","lemmaId":0,"lemmaTitle":"古太古代"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"36"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"蓝绿藻出现"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"7"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"中太古代","lemmaId":0,"lemmaTitle":"中太古代"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"32"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"原核生物进一步发展"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"8"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"新太古代","lemmaId":0,"lemmaTitle":"新太古代"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"28"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"第一次冰河期"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"9"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"元古宙","lemmaId":0,"lemmaTitle":"元古宙"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"成铁纪","lemmaId":0,"lemmaTitle":"成铁纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"25"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[]}],[{"type":"data","content":[{"tag":"text","text":"10"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"层侵纪","lemmaId":0,"lemmaTitle":"层侵纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"23"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[]}],[{"type":"data","content":[{"tag":"text","text":"11"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"造山纪","lemmaId":0,"lemmaTitle":"造山纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"20.5"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[]}],[{"type":"data","content":[{"tag":"text","text":"12"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"古元古代","lemmaId":0,"lemmaTitle":"古元古代"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"固结纪","lemmaId":0,"lemmaTitle":"固结纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"18"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[]}],[{"type":"data","content":[{"tag":"text","text":"13"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"盖层纪","lemmaId":0,"lemmaTitle":"盖层纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"16"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[]}],[{"type":"data","content":[{"tag":"text","text":"14"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"延展纪","lemmaId":0,"lemmaTitle":"延展纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"14"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[]}],[{"type":"data","content":[{"tag":"text","text":"15"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"中元古代","lemmaId":0,"lemmaTitle":"中元古代"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"狭带纪","lemmaId":0,"lemmaTitle":"狭带纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"12"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[]}],[{"type":"data","content":[{"tag":"text","text":"16"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"拉伸纪","lemmaId":0,"lemmaTitle":"拉伸纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"10"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"罗迪尼亚古陆形成"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"17"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"成冰纪","lemmaId":0,"lemmaTitle":"成冰纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"8.50"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"发生雪球事件"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"18"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"新元古代","lemmaId":0,"lemmaTitle":"新元古代"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"埃迪卡拉纪","lemmaId":0,"lemmaTitle":"埃迪卡拉纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"6.3"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"多细胞生物出现"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"19"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"显生宙","lemmaId":0,"lemmaTitle":"显生宙"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"古生代","lemmaId":0,"lemmaTitle":"古生代"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"寒武纪","lemmaId":74445,"lemmaTitle":"寒武纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"5.42"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"寒武纪生命大爆发"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"20"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"奥陶纪","lemmaId":0,"lemmaTitle":"奥陶纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"4.883"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"鱼类出现；海生藻类繁盛"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"21"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"志留纪","lemmaId":0,"lemmaTitle":"志留纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"4.437"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"陆生的裸蕨植物出现"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"22"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"泥盆纪","lemmaId":0,"lemmaTitle":"泥盆纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"4.16"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"鱼类繁荣；两栖动物出现；昆虫出现；裸子植物出现；石松和木贼出现"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"23"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"石炭纪","lemmaId":0,"lemmaTitle":"石炭纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"3.592"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"昆虫繁荣；爬行动物出现；煤炭森林"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"24"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"二叠纪","lemmaId":0,"lemmaTitle":"二叠纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"2.99"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"二叠纪灭绝事件，地球上95%生物灭绝；盘古大陆形成"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"25"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"中生代","lemmaId":0,"lemmaTitle":"中生代"},{"tag":"text","text":"、"},{"tag":"innerlink","text":"三叠纪","lemmaId":0,"lemmaTitle":"三叠纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"2.51"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"恐龙出现；卵生哺乳动物出现"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"26"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"侏罗纪","lemmaId":0,"lemmaTitle":"侏罗纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"1.996"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"有袋类哺乳动物出现；鸟类出现；裸子植物繁荣；被子植物出现"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"27"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"白垩纪","lemmaId":0,"lemmaTitle":"白垩纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"0.996."},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"恐龙的繁荣和灭绝、白垩纪-第三纪灭绝事件，地球上45%生物灭绝，有胎盘的哺乳动物出现"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"28"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"innerlink","text":"第三纪","lemmaId":1196494,"lemmaTitle":"第三纪"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"未知"},{"tag":"text","text":"","br":true}]},{"type":"data","content":[{"tag":"text","text":"动植物都接近现代"},{"tag":"text","text":"","br":true}]}],[{"type":"data","content":[{"tag":"text","text":"29"}]},{"type":"data","content":[{"tag":"innerlink","text":"第四纪","lemmaId":634418,"lemmaTitle":"第四纪"}]},{"type":"data","content":[{"tag":"text","text":"0.0621"}]},{"type":"data","content":[{"tag":"innerlink","text":"人类","lemmaId":31910,"lemmaTitle":"人类"},{"tag":"text","text":"出现"}]}]],"length":587,"lines":35,"pid":"p-95","level":1,"index":"8"},{"tag":"header","number":2,"title":"地球年龄","length":4,"lines":1,"pid":"p-96","level":1,"index":"9"},{"tag":"paragraph","content":[{"tag":"text","text":"21世纪科学家对地球的年龄再次进行了确认，认为地球产生要远远晚于太阳系产生的时间，跨度约为1.5亿年左右，这远远晚于此前认为的30万～4500万年。此前科学家通过太阳系年龄计算公式算出了太阳系产生的时间为55.68亿年前，而地球产生的年龄要比太阳系晚30亿年到45亿年左右，大约为25.48亿年前左右。在2007年时，瑞士的科学家对此数据进行了修正，认为地球的产生要在太阳系形成的6200万年之后。"}],"length":199,"lines":12,"pid":"p-97","level":1,"index":"9"},{"tag":"paragraph","content":[{"tag":"text","text":"科学家一般是通过同位元素铪182和钨182两种放射元素来计算地球和月球年龄的。铪182的衰变期为900万年衰变之后的同位素为钨182，而钨182则是地核的组成部分之一。科学家们认为在地球形成时，几乎所有的铪182元素全部已经衰变成了钨182。仅有极少量存在，正是这微量的铪182才能够帮助科学家测算地球的真实年龄。尼尔斯研究所的教授说道：\u201c所有的铪完全衰变成钨需要50亿～60亿年的时间，并且都会沉在地核，而新的表明，地球和月球上地幔含有的元素量高于太阳系，而经过测算时间大约为1.5亿年。\u201d"}],"length":246,"lines":14,"pid":"p-98","level":1,"index":"9"},{"tag":"header","number":2,"title":"地球卫星","length":4,"lines":1,"pid":"p-99","level":1,"index":"10"},{"tag":"paragraph","content":[{"tag":"text","text":"主词条："},{"tag":"innerlink","text":"地球卫星","lemmaId":0,"lemmaTitle":"地球卫星"},{"tag":"text","text":"，"},{"tag":"innerlink","text":"月球","lemmaId":30767,"lemmaTitle":"月球"}],"length":11,"lines":1,"pid":"p-100","level":1,"index":"10"},{"tag":"paragraph","content":[{"tag":"text","text":"地球有一个卫星月球，月球俗称月亮，也称太阴。在太阳系中是地球唯一的"},{"tag":"innerlink","text":"天然卫星","lemmaId":0,"lemmaTitle":"天然卫星"},{"tag":"text","text":"。月球是最明显的天然卫星的例子。在太阳系里，除水星和金星外，其他行星里面都有天然卫星。月球直径约3476公里，是地球的1/4。体积只有地球的1/49，质量约7350亿亿吨，相当于地球质量的1/81，月球表面的重力差不多是地球重力的1/6。"},{"tag":"ref","ctrId":"","index":6,"data":null}],"length":156,"lines":9,"pid":"p-102","float":[{"tag":"module","type":"album","moduleId":15439670,"data":{"basic":{"modulename":"album","desc":"地球","albumTag":"","url":"cover=3792cb39cdd3f1be3b87ce06&count=19&desc=%E5%9C%B0%E7%90%83","total":19,"coverpic":"3792cb39cdd3f1be3b87ce06","width":220,"height":220,"style":"float: right;width:220px;height:220px;","layout":"right","moduleId":15439670,"moduleVersionId":279356459,"moduleType":2,"picId":1305203579,"type":{"view":220,"albumview":200,"oriWithWater":221,"litterImage":200,"authModule":150,"view_new":209,"view_card":268},"sizes":{"220":{"url":"https://bkimg.cdn.bcebos.com/pic/0b46f21fbe096b638df7a02d0c338744eaf8ace5?x-bce-process=image/resize,m_lfit,w_220,h_220,limit_1","width":220,"height":220,"size":220},"200":{"url":"https://bkimg.cdn.bcebos.com/pic/0b46f21fbe096b638df7a02d0c338744eaf8ace5?x-bce-process=image/resize,m_lfit,w_200,h_200,limit_1","width":null,"height":null,"size":200},"221":{"url":"https://bkimg.cdn.bcebos.com/pic/0b46f21fbe096b638df7a02d0c338744eaf8ace5?x-bce-process=image/watermark,g_7,image_d2F0ZXIvYmFpa2U2MA==,xp_5,yp_5","width":220,"height":220,"size":221},"150":{"url":"https://bkimg.cdn.bcebos.com/pic/0b46f21fbe096b638df7a02d0c338744eaf8ace5?x-bce-process=image/resize,m_lfit,w_150,h_150,limit_1","width":220,"height":220,"size":150},"209":{"url":"https://bkimg.cdn.bcebos.com/pic/0b46f21fbe096b638df7a02d0c338744eaf8ace5?x-bce-process=image/resize,m_lfit,w_209,limit_1","width":209,"height":209,"size":209},"268":{"url":"https://bkimg.cdn.bcebos.com/pic/0b46f21fbe096b638df7a02d0c338744eaf8ace5?x-bce-process=image/resize,m_lfit,w_268,limit_1","width":220,"height":220,"size":268}}},"toppics":[{"src":"3792cb39cdd3f1be3b87ce06","owner":"","desc":"地球","width":220,"height":220,"url":"https://bkimg.cdn.bcebos.com/pic/3792cb39cdd3f1be3b87ce06?x-bce-process=image/resize,m_fill,w_600,h_600,align_0"},{"src":"0d729944802bd101500ffe06","owner":"jm12262762","desc":"地球概貌图","height":768,"width":773,"url":"https://bkimg.cdn.bcebos.com/pic/0d729944802bd101500ffe06?x-bce-process=image/resize,m_fill,w_300,h_300,align_50"},{"src":"eab9044c3cdc12b4d62afc06","owner":"jm12262762","desc":"地球概貌图","height":503,"width":500,"url":"https://bkimg.cdn.bcebos.com/pic/eab9044c3cdc12b4d62afc06?x-bce-process=image/resize,m_fill,w_300,h_300,align_0"}],"toppicscount":3},"pid":"p-101"}],"level":1,"index":"10"}]
             */

            private int errno;
            private String errmsg;
            private int lemmaId;
            private String lemmaTitle;
            private String lemmaDesc;
            private String url;
            private String summary;
            private String picUrl;
            private List<CardBean> card;
            private List<CatalogContentStructuredBean> catalogContentStructured;

            public int getErrno() {
                return errno;
            }

            public void setErrno(int errno) {
                this.errno = errno;
            }

            public String getErrmsg() {
                return errmsg;
            }

            public void setErrmsg(String errmsg) {
                this.errmsg = errmsg;
            }

            public int getLemmaId() {
                return lemmaId;
            }

            public void setLemmaId(int lemmaId) {
                this.lemmaId = lemmaId;
            }

            public String getLemmaTitle() {
                return lemmaTitle;
            }

            public void setLemmaTitle(String lemmaTitle) {
                this.lemmaTitle = lemmaTitle;
            }

            public String getLemmaDesc() {
                return lemmaDesc;
            }

            public void setLemmaDesc(String lemmaDesc) {
                this.lemmaDesc = lemmaDesc;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public List<CardBean> getCard() {
                return card;
            }

            public void setCard(List<CardBean> card) {
                this.card = card;
            }

            public List<CatalogContentStructuredBean> getCatalogContentStructured() {
                return catalogContentStructured;
            }

            public void setCatalogContentStructured(List<CatalogContentStructuredBean> catalogContentStructured) {
                this.catalogContentStructured = catalogContentStructured;
            }

            public static class CardBean {
                /**
                 * key : m36_cname
                 * name : 中文名
                 * value : ["地球"]
                 */

                private String key;
                private String name;
                private List<String> value;

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<String> getValue() {
                    return value;
                }

                public void setValue(List<String> value) {
                    this.value = value;
                }
            }

            public static class CatalogContentStructuredBean {

                private String tag;
                private int number;
                private String title;
                private int length;
                private int lines;
                private String pid;
                private int level;
                private String index;
                private Object caption;
                private boolean complex;
                private List<ContentBean> content;
                @SerializedName("float")
                private List<FloatBean> floatX;
                private List<List<DataBeanX>> data;

                public String getTag() {
                    return tag;
                }

                public void setTag(String tag) {
                    this.tag = tag;
                }

                public int getNumber() {
                    return number;
                }

                public void setNumber(int number) {
                    this.number = number;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getLength() {
                    return length;
                }

                public void setLength(int length) {
                    this.length = length;
                }

                public int getLines() {
                    return lines;
                }

                public void setLines(int lines) {
                    this.lines = lines;
                }

                public String getPid() {
                    return pid;
                }

                public void setPid(String pid) {
                    this.pid = pid;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public String getIndex() {
                    return index;
                }

                public void setIndex(String index) {
                    this.index = index;
                }

                public Object getCaption() {
                    return caption;
                }

                public void setCaption(Object caption) {
                    this.caption = caption;
                }

                public boolean isComplex() {
                    return complex;
                }

                public void setComplex(boolean complex) {
                    this.complex = complex;
                }

                public List<ContentBean> getContent() {
                    return content;
                }

                public void setContent(List<ContentBean> content) {
                    this.content = content;
                }

                public List<FloatBean> getFloatX() {
                    return floatX;
                }

                public void setFloatX(List<FloatBean> floatX) {
                    this.floatX = floatX;
                }

                public List<List<DataBeanX>> getData() {
                    return data;
                }

                public void setData(List<List<DataBeanX>> data) {
                    this.data = data;
                }

                public static class ContentBean {
                    /**
                     * tag : text
                     * text : 主词条：
                     * lemmaId : 0
                     * lemmaTitle : 地球历史
                     */

                    private String tag;
                    private String text;
                    private int lemmaId;
                    private String lemmaTitle;

                    public String getTag() {
                        return tag;
                    }

                    public void setTag(String tag) {
                        this.tag = tag;
                    }

                    public String getText() {
                        return text;
                    }

                    public void setText(String text) {
                        this.text = text;
                    }

                    public int getLemmaId() {
                        return lemmaId;
                    }

                    public void setLemmaId(int lemmaId) {
                        this.lemmaId = lemmaId;
                    }

                    public String getLemmaTitle() {
                        return lemmaTitle;
                    }

                    public void setLemmaTitle(String lemmaTitle) {
                        this.lemmaTitle = lemmaTitle;
                    }
                }

                public static class FloatBean {
                    /**
                     * tag : module
                     * type : album
                     * moduleId : 13065065
                     * pid : p-2
                     */

                    private String tag;
                    private String type;
                    private int moduleId;
                    private DataBean data;
                    private String pid;

                    public String getTag() {
                        return tag;
                    }

                    public void setTag(String tag) {
                        this.tag = tag;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public int getModuleId() {
                        return moduleId;
                    }

                    public void setModuleId(int moduleId) {
                        this.moduleId = moduleId;
                    }

                    public DataBean getData() {
                        return data;
                    }

                    public void setData(DataBean data) {
                        this.data = data;
                    }

                    public String getPid() {
                        return pid;
                    }

                    public void setPid(String pid) {
                        this.pid = pid;
                    }

                    public static class DataBean {
                        /**
                         * toppicscount : 3
                         */

                        private BasicBean basic;
                        private int toppicscount;
                        private List<ToppicsBean> toppics;

                        public BasicBean getBasic() {
                            return basic;
                        }

                        public void setBasic(BasicBean basic) {
                            this.basic = basic;
                        }

                        public int getToppicscount() {
                            return toppicscount;
                        }

                        public void setToppicscount(int toppicscount) {
                            this.toppicscount = toppicscount;
                        }

                        public List<ToppicsBean> getToppics() {
                            return toppics;
                        }

                        public void setToppics(List<ToppicsBean> toppics) {
                            this.toppics = toppics;
                        }

                        public static class BasicBean {
                            /**
                             * modulename : album
                             * desc : 50亿年以前的太阳系
                             * albumTag :
                             * url : cover=aa59892bcbe998b4e7cd4019&count=4&desc=50%E4%BA%BF%E5%B9%B4%E4%BB%A5%E5%89%8D%E7%9A%84%E5%A4%AA%E9%98%B3%E7%B3%BB
                             * total : 4
                             * coverpic : aa59892bcbe998b4e7cd4019
                             * width : 219
                             * height : 170
                             * style : float: right;width:219px;height:170px;
                             * layout : right
                             * moduleId : 13065065
                             * moduleVersionId : 279356458
                             * moduleType : 2
                             * picId : 1320021535
                             * type : {"view":220,"albumview":200,"oriWithWater":220,"litterImage":200,"authModule":150,"view_new":209,"view_card":268}
                             * sizes : {"220":{"url":"https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/watermark,g_7,image_d2F0ZXIvYmFpa2U2MA==,xp_5,yp_5","width":219,"height":170,"size":220},"200":{"url":"https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/resize,m_lfit,w_200,h_200,limit_1","width":200,"height":155,"size":200},"150":{"url":"https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/resize,m_lfit,w_150,limit_1","width":150,"height":116,"size":150},"209":{"url":"https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/resize,m_lfit,w_209,limit_1","width":209,"height":162,"size":209},"268":{"url":"https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/resize,m_lfit,w_268,limit_1","width":219,"height":170,"size":268}}
                             */

                            private String modulename;
                            private String desc;
                            private String albumTag;
                            private String url;
                            private int total;
                            private String coverpic;
                            private int width;
                            private int height;
                            private String style;
                            private String layout;
                            private int moduleId;
                            private int moduleVersionId;
                            private int moduleType;
                            private long picId;
                            private TypeBean type;
                            private SizesBean sizes;

                            public String getModulename() {
                                return modulename;
                            }

                            public void setModulename(String modulename) {
                                this.modulename = modulename;
                            }

                            public String getDesc() {
                                return desc;
                            }

                            public void setDesc(String desc) {
                                this.desc = desc;
                            }

                            public String getAlbumTag() {
                                return albumTag;
                            }

                            public void setAlbumTag(String albumTag) {
                                this.albumTag = albumTag;
                            }

                            public String getUrl() {
                                return url;
                            }

                            public void setUrl(String url) {
                                this.url = url;
                            }

                            public int getTotal() {
                                return total;
                            }

                            public void setTotal(int total) {
                                this.total = total;
                            }

                            public String getCoverpic() {
                                return coverpic;
                            }

                            public void setCoverpic(String coverpic) {
                                this.coverpic = coverpic;
                            }

                            public int getWidth() {
                                return width;
                            }

                            public void setWidth(int width) {
                                this.width = width;
                            }

                            public int getHeight() {
                                return height;
                            }

                            public void setHeight(int height) {
                                this.height = height;
                            }

                            public String getStyle() {
                                return style;
                            }

                            public void setStyle(String style) {
                                this.style = style;
                            }

                            public String getLayout() {
                                return layout;
                            }

                            public void setLayout(String layout) {
                                this.layout = layout;
                            }

                            public int getModuleId() {
                                return moduleId;
                            }

                            public void setModuleId(int moduleId) {
                                this.moduleId = moduleId;
                            }

                            public int getModuleVersionId() {
                                return moduleVersionId;
                            }

                            public void setModuleVersionId(int moduleVersionId) {
                                this.moduleVersionId = moduleVersionId;
                            }

                            public int getModuleType() {
                                return moduleType;
                            }

                            public void setModuleType(int moduleType) {
                                this.moduleType = moduleType;
                            }

                            public long getPicId() {
                                return picId;
                            }

                            public void setPicId(long picId) {
                                this.picId = picId;
                            }

                            public TypeBean getType() {
                                return type;
                            }

                            public void setType(TypeBean type) {
                                this.type = type;
                            }

                            public SizesBean getSizes() {
                                return sizes;
                            }

                            public void setSizes(SizesBean sizes) {
                                this.sizes = sizes;
                            }

                            public static class TypeBean {
                                /**
                                 * view : 220
                                 * albumview : 200
                                 * oriWithWater : 220
                                 * litterImage : 200
                                 * authModule : 150
                                 * view_new : 209
                                 * view_card : 268
                                 */

                                private int view;
                                private int albumview;
                                private int oriWithWater;
                                private int litterImage;
                                private int authModule;
                                private int view_new;
                                private int view_card;

                                public int getView() {
                                    return view;
                                }

                                public void setView(int view) {
                                    this.view = view;
                                }

                                public int getAlbumview() {
                                    return albumview;
                                }

                                public void setAlbumview(int albumview) {
                                    this.albumview = albumview;
                                }

                                public int getOriWithWater() {
                                    return oriWithWater;
                                }

                                public void setOriWithWater(int oriWithWater) {
                                    this.oriWithWater = oriWithWater;
                                }

                                public int getLitterImage() {
                                    return litterImage;
                                }

                                public void setLitterImage(int litterImage) {
                                    this.litterImage = litterImage;
                                }

                                public int getAuthModule() {
                                    return authModule;
                                }

                                public void setAuthModule(int authModule) {
                                    this.authModule = authModule;
                                }

                                public int getView_new() {
                                    return view_new;
                                }

                                public void setView_new(int view_new) {
                                    this.view_new = view_new;
                                }

                                public int getView_card() {
                                    return view_card;
                                }

                                public void setView_card(int view_card) {
                                    this.view_card = view_card;
                                }
                            }

                            public static class SizesBean {
                                /**
                                 * 220 : {"url":"https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/watermark,g_7,image_d2F0ZXIvYmFpa2U2MA==,xp_5,yp_5","width":219,"height":170,"size":220}
                                 * 200 : {"url":"https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/resize,m_lfit,w_200,h_200,limit_1","width":200,"height":155,"size":200}
                                 * 150 : {"url":"https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/resize,m_lfit,w_150,limit_1","width":150,"height":116,"size":150}
                                 * 209 : {"url":"https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/resize,m_lfit,w_209,limit_1","width":209,"height":162,"size":209}
                                 * 268 : {"url":"https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/resize,m_lfit,w_268,limit_1","width":219,"height":170,"size":268}
                                 */

                                @SerializedName("220")
                                private _$220Bean _$220;
                                @SerializedName("200")
                                private _$200Bean _$200;
                                @SerializedName("150")
                                private _$150Bean _$150;
                                @SerializedName("209")
                                private _$209Bean _$209;
                                @SerializedName("268")
                                private _$268Bean _$268;

                                public _$220Bean get_$220() {
                                    return _$220;
                                }

                                public void set_$220(_$220Bean _$220) {
                                    this._$220 = _$220;
                                }

                                public _$200Bean get_$200() {
                                    return _$200;
                                }

                                public void set_$200(_$200Bean _$200) {
                                    this._$200 = _$200;
                                }

                                public _$150Bean get_$150() {
                                    return _$150;
                                }

                                public void set_$150(_$150Bean _$150) {
                                    this._$150 = _$150;
                                }

                                public _$209Bean get_$209() {
                                    return _$209;
                                }

                                public void set_$209(_$209Bean _$209) {
                                    this._$209 = _$209;
                                }

                                public _$268Bean get_$268() {
                                    return _$268;
                                }

                                public void set_$268(_$268Bean _$268) {
                                    this._$268 = _$268;
                                }

                                public static class _$220Bean {
                                    /**
                                     * url : https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/watermark,g_7,image_d2F0ZXIvYmFpa2U2MA==,xp_5,yp_5
                                     * width : 219
                                     * height : 170
                                     * size : 220
                                     */

                                    private String url;
                                    private int width;
                                    private int height;
                                    private int size;

                                    public String getUrl() {
                                        return url;
                                    }

                                    public void setUrl(String url) {
                                        this.url = url;
                                    }

                                    public int getWidth() {
                                        return width;
                                    }

                                    public void setWidth(int width) {
                                        this.width = width;
                                    }

                                    public int getHeight() {
                                        return height;
                                    }

                                    public void setHeight(int height) {
                                        this.height = height;
                                    }

                                    public int getSize() {
                                        return size;
                                    }

                                    public void setSize(int size) {
                                        this.size = size;
                                    }
                                }

                                public static class _$200Bean {
                                    /**
                                     * url : https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/resize,m_lfit,w_200,h_200,limit_1
                                     * width : 200
                                     * height : 155
                                     * size : 200
                                     */

                                    private String url;
                                    private int width;
                                    private int height;
                                    private int size;

                                    public String getUrl() {
                                        return url;
                                    }

                                    public void setUrl(String url) {
                                        this.url = url;
                                    }

                                    public int getWidth() {
                                        return width;
                                    }

                                    public void setWidth(int width) {
                                        this.width = width;
                                    }

                                    public int getHeight() {
                                        return height;
                                    }

                                    public void setHeight(int height) {
                                        this.height = height;
                                    }

                                    public int getSize() {
                                        return size;
                                    }

                                    public void setSize(int size) {
                                        this.size = size;
                                    }
                                }

                                public static class _$150Bean {
                                    /**
                                     * url : https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/resize,m_lfit,w_150,limit_1
                                     * width : 150
                                     * height : 116
                                     * size : 150
                                     */

                                    private String url;
                                    private int width;
                                    private int height;
                                    private int size;

                                    public String getUrl() {
                                        return url;
                                    }

                                    public void setUrl(String url) {
                                        this.url = url;
                                    }

                                    public int getWidth() {
                                        return width;
                                    }

                                    public void setWidth(int width) {
                                        this.width = width;
                                    }

                                    public int getHeight() {
                                        return height;
                                    }

                                    public void setHeight(int height) {
                                        this.height = height;
                                    }

                                    public int getSize() {
                                        return size;
                                    }

                                    public void setSize(int size) {
                                        this.size = size;
                                    }
                                }

                                public static class _$209Bean {
                                    /**
                                     * url : https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/resize,m_lfit,w_209,limit_1
                                     * width : 209
                                     * height : 162
                                     * size : 209
                                     */

                                    private String url;
                                    private int width;
                                    private int height;
                                    private int size;

                                    public String getUrl() {
                                        return url;
                                    }

                                    public void setUrl(String url) {
                                        this.url = url;
                                    }

                                    public int getWidth() {
                                        return width;
                                    }

                                    public void setWidth(int width) {
                                        this.width = width;
                                    }

                                    public int getHeight() {
                                        return height;
                                    }

                                    public void setHeight(int height) {
                                        this.height = height;
                                    }

                                    public int getSize() {
                                        return size;
                                    }

                                    public void setSize(int size) {
                                        this.size = size;
                                    }
                                }

                                public static class _$268Bean {
                                    /**
                                     * url : https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc038dab7bc6d43f8794a5c226f8?x-bce-process=image/resize,m_lfit,w_268,limit_1
                                     * width : 219
                                     * height : 170
                                     * size : 268
                                     */

                                    private String url;
                                    private int width;
                                    private int height;
                                    private int size;

                                    public String getUrl() {
                                        return url;
                                    }

                                    public void setUrl(String url) {
                                        this.url = url;
                                    }

                                    public int getWidth() {
                                        return width;
                                    }

                                    public void setWidth(int width) {
                                        this.width = width;
                                    }

                                    public int getHeight() {
                                        return height;
                                    }

                                    public void setHeight(int height) {
                                        this.height = height;
                                    }

                                    public int getSize() {
                                        return size;
                                    }

                                    public void setSize(int size) {
                                        this.size = size;
                                    }
                                }
                            }
                        }

                        public static class ToppicsBean {
                            /**
                             * src : aa59892bcbe998b4e7cd4019
                             * owner :
                             * desc : 50亿年以前的太阳系
                             * width : 640
                             * height : 480
                             * url : https://bkimg.cdn.bcebos.com/pic/aa59892bcbe998b4e7cd4019?x-bce-process=image/resize,m_fill,w_600,h_600,align_50
                             */

                            private String src;
                            private String owner;
                            private String desc;
                            private int width;
                            private int height;
                            private String url;

                            public String getSrc() {
                                return src;
                            }

                            public void setSrc(String src) {
                                this.src = src;
                            }

                            public String getOwner() {
                                return owner;
                            }

                            public void setOwner(String owner) {
                                this.owner = owner;
                            }

                            public String getDesc() {
                                return desc;
                            }

                            public void setDesc(String desc) {
                                this.desc = desc;
                            }

                            public int getWidth() {
                                return width;
                            }

                            public void setWidth(int width) {
                                this.width = width;
                            }

                            public int getHeight() {
                                return height;
                            }

                            public void setHeight(int height) {
                                this.height = height;
                            }

                            public String getUrl() {
                                return url;
                            }

                            public void setUrl(String url) {
                                this.url = url;
                            }
                        }
                    }
                }

                public static class DataBeanX {
                    /**
                     * colspan : 3
                     * type : header
                     * content : [{"tag":"text","text":"地质时期"},{"tag":"text","text":"","br":true}]
                     */

                    private int colspan;
                    private String type;
                    private List<ContentBeanX> content;

                    public int getColspan() {
                        return colspan;
                    }

                    public void setColspan(int colspan) {
                        this.colspan = colspan;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public List<ContentBeanX> getContent() {
                        return content;
                    }

                    public void setContent(List<ContentBeanX> content) {
                        this.content = content;
                    }

                    public static class ContentBeanX {
                        /**
                         * tag : text
                         * text : 地质时期
                         * br : true
                         */

                        private String tag;
                        private String text;
                        private boolean br;

                        public String getTag() {
                            return tag;
                        }

                        public void setTag(String tag) {
                            this.tag = tag;
                        }

                        public String getText() {
                            return text;
                        }

                        public void setText(String text) {
                            this.text = text;
                        }

                        public boolean isBr() {
                            return br;
                        }

                        public void setBr(boolean br) {
                            this.br = br;
                        }
                    }
                }
            }

        }
    }

    public void outTag(){
        LUtils.i("父类tag  =  "+tag);
    }


}
