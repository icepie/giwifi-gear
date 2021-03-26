# giwifi-gear-mix

A cli tool for login giwifi (for special auth methods)

## 简介

这里是 [giwifi-gear](https://github.com/icepie/giwifi-gear) 项目的一个分支

主要为未开启通用 `网页认证` 接口的 `GiWiFi` 提供一个认证方式

有些接口我所在的地方并没法使用, 所以在这里感谢大家的测试~

## 进度

### 网页接口 (Web Api)

#### 教职工 (staff)

- [x] py - 移植于 [giwifi-gear.py](https://github.com/icepie/giwifi-gear/tree/py) - 不保证所有参数功能可用

- [x] sh - 移植于 [giwifi-gear.sh](https://github.com/icepie/giwifi-gear/tree/sh) - 不保证所有参数功能可用

#### 特殊终端以及校外访客 (special)

- [ ] py - 咕咕咕 - 额外需要 [giwifi-mix-crypto](https://github.com/icepie/giwifi-gear/tree/mix/crypto-tool) 进行加密, 自行配置

- [x] sh - 已实现认证与绑定...不定期更新 - 额外需要 [giwifi-mix-crypto](https://github.com/icepie/giwifi-gear/tree/mix/crypto-tool) 进行加密, 自行配置

#### 定制版本

##### 潍坊学院 (wfu)

- [x] py - [@Tribunny](https://github.com/Tribunny)实现 - 额外需要 [giwifi-mix-crypto](https://github.com/icepie/giwifi-gear/tree/mix/crypto-tool) 进行加密, 自行配置

- [x] sh - 不定期更新 - 额外需要 [giwifi-mix-crypto](https://github.com/icepie/giwifi-gear/tree/mix/crypto-tool) 进行加密, 自行配置

### 客户端接口 (App Api)

#### 移动端 (phone)

- [ ] py - 预期计划中, 已拿到核心算法

#### 桌面端 (pc)

- [x] py - 已经支持 `win` 和 `mac` 的客户端认证方式, 有空完善 - 不支持客户端接口 `pc` 和 `phone` 走同一个的接口认证方式(那玩意以后单独拿出来鞭尸...)

- [ ] sh - 有空补上去

## 其他

注意: 使用方法各个版本可能有所差异, 请自己耐心研究, 如遇到不懂的地方可以提 [issue](https://github.com/icepie/giwifi-gear/issues)

