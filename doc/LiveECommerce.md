# [直播电商](https://app.mockplus.cn/app/s3pFHanJ94P/specs/rp/OpI9CyNhl)

## 系统用例
```plantuml
left to right direction


actor "管理员" as admin
actor "澳觅用户" as user
actor "主播" as anchor
actor "场控" as field_ctrlor

package "管理后台" {
    usecase "直播活动管理\n（新增、编辑、删除、备播、\n开播、分享码、控制台、复盘）" as livestream_activity
    usecase "直播活动-备货\n（电商商品、团购）" as livestream_activity_goods
    usecase "直播活动-发券\n（主播券、直播间券）" as livestream_activity_coupon
    usecase "直播活动-控评\n（黑白名单、敏感词）" as livestream_activity_ctrl_evaluate
    usecase "商品管理\n（添加、编辑、批量上下架）" as goods
    usecase "阶梯运费配置" as order_ships
    usecase "订单管理\n（退款）" as order
    usecase "售后维权\n（受理用户退款）" as order_refund
    
    usecase "控制台\n（实时数据、气氛组工具包、直播商品、\n用户评论、提词卡）" as console
    usecase "主播大屏\n（实时数据、直播商品、提词卡、用户评论）" as anchor_screen
    
    admin --> livestream_activity
    admin --> livestream_activity_goods
    admin --> livestream_activity_coupon
    admin --> livestream_activity_ctrl_evaluate
    admin --> goods
    admin --> order_ships
    admin --> order
    admin --> order_refund
    
    field_ctrlor --> console
    
    anchor --> anchor_screen
}

package "澳觅App" {
    usecase "观看直播" as livestrem_watch
    usecase "直播互动\n（点赞、评论、转发、\n领券、求补货、想要）" as livestream_interactive
    usecase "商品橱窗" as goods_window
    usecase "商品详情" as goods_info
    usecase "直播下单" as submit_order
    usecase "订单列表" as order_list
    usecase "订单详情" as order_info
    usecase "申请售后" as submit_order_refund
    
    user --> livestrem_watch
    user --> livestream_interactive
    user --> goods_window
    user --> goods_info
    user --> submit_order
    user --> order_list
    user --> order_info
    user --> submit_order_refund
}

```

## 系统上下文
```plantuml



```

## 数据模型

### 商品
```plantuml

!define table(x) class x << (T,#ffebf3) >>
!define table(x, desc) class "desc" as x << (T,#ffebf3) x >>
!define domain(desc, x) rectangle desc as x 
'hide stereotypes
hide field
hide methods

domain("商品域", goods_domain){
    table(goods, "商品信息") {}
    table(goods_sku, "商品规格") {}

    goods "1" --> "0..*" goods_sku: 商品多规格
}

```

### 运费
```plantuml

!define table(x) class x << (T,#ffebf3) >>
!define table(x, desc) class "desc" as x << (T,#ffebf3) x >>
!define domain(desc, x) rectangle desc as x 
'hide stereotypes
hide field
hide methods


domain("运费配置域", ships_config_domain){
    table(order_ships_config, "订单运费配置") {}
    table(order_ships_discount_rule, "订单运费优惠规则") {}
}

```

### 直播活动
```plantuml

!define table(x) class x << (T,#ffebf3) >>
!define table(x, desc) class "desc" as x << (T,#ffebf3) x >>
!define domain(desc, x) rectangle desc as x 
'hide stereotypes
hide field
hide methods


```

## 