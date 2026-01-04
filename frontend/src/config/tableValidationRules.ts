/**
 * 表格验证规则配置
 * 用于H5表单的字段验证和自动计算
 */

export interface AutoCalcRule {
  /** 计算结果字段 */
  targetField: string
  /** 依赖的字段数组 */
  sourceFields: string[]
  /** 计算方式: sum=求和 */
  calcType: 'sum'
  /** 提示信息 */
  message?: string
}

export interface CompareRule {
  /** 被验证字段 */
  field: string
  /** 操作符 */
  operator: '>=' | '<=' | '>' | '<' | '='
  /** 参照字段（与 compareFields 二选一） */
  compareField?: string
  /** 参照字段数组（用于多字段求和比较，与 compareField 二选一） */
  compareFields?: string[]
  /** 错误提示 */
  errorMessage: string
}

export interface TableValidationConfig {
  /** 表格代码 */
  tableCode?: string
  /** 自动计算规则 */
  autoCalcRules?: AutoCalcRule[]
  /** 比较验证规则 */
  compareRules?: CompareRule[]
}

/**
 * 所有表格的验证规则配置
 */
export const tableValidationConfig: Record<string, TableValidationConfig> = {
  // ==================== A01 人员受灾情况统计表 ====================
  A01: {
    autoCalcRules: [
      {
        targetField: 'colA01007',
        sourceFields: ['colA01008', 'colA01009'],
        calcType: 'sum',
        message: '紧急转移安置人口 = 集中安置人口 + 分散安置人口'
      }
    ],
    compareRules: [
      { field: 'colA01001', operator: '>=', compareField: 'colA01002', errorMessage: '受灾人口不能小于因灾死亡人口' },
      { field: 'colA01001', operator: '>=', compareField: 'colA01003', errorMessage: '受灾人口不能小于因灾失踪人口' },
      { field: 'colA01001', operator: '>=', compareField: 'colA01004', errorMessage: '受灾人口不能小于因灾受伤人口' },
      { field: 'colA01001', operator: '>=', compareField: 'colA01006', errorMessage: '受灾人口不能小于饮水困难人口' },
      { field: 'colA01001', operator: '>=', compareField: 'colA01007', errorMessage: '受灾人口不能小于紧急转移安置人口' },
      { field: 'colA01001', operator: '>=', compareField: 'colA01010', errorMessage: '受灾人口不能小于需紧急生活救助人口' },
      { field: 'colA01004', operator: '>=', compareField: 'colA01005', errorMessage: '因灾受伤人口不能小于因灾重伤人口' },
      { field: 'colA01010', operator: '>=', compareField: 'colA01011', errorMessage: '需紧急生活救助人口不能小于需过渡期生活救助人口' },
      { field: 'colA01011', operator: '>=', compareField: 'colA01012', errorMessage: '需过渡期生活救助人口不能小于女性人口' },
      { field: 'colA01011', operator: '>=', compareField: 'colA01013', errorMessage: '需过渡期生活救助人口不能小于老人（60岁+）' },
      { field: 'colA01011', operator: '>=', compareField: 'colA01014', errorMessage: '需过渡期生活救助人口不能小于儿童（18岁以下）' },
      { field: 'colA01011', operator: '>=', compareField: 'colA01015', errorMessage: '需过渡期生活救助人口不能小于三无人员' },
      { field: 'colA01011', operator: '>=', compareField: 'colA01016', errorMessage: '需过渡期生活救助人口不能小于三孤人员' }
    ]
  },

  // ==================== B01 农村居民住房受损情况统计表 ====================
  B01: {
    autoCalcRules: [
      { targetField: 'colB01016', sourceFields: ['colB01001', 'colB01004', 'colB01007', 'colB01010', 'colB01013'], calcType: 'sum', message: '倒塌住房总户数 = 各结构户数之和' },
      { targetField: 'colB01017', sourceFields: ['colB01002', 'colB01005', 'colB01008', 'colB01011', 'colB01014'], calcType: 'sum', message: '倒塌住房总间数 = 各结构间数之和' },
      { targetField: 'colB01018', sourceFields: ['colB01003', 'colB01006', 'colB01009', 'colB01012', 'colB01015'], calcType: 'sum', message: '倒塌住房经济损失 = 各结构经济损失之和' },
      { targetField: 'colB01034', sourceFields: ['colB01019', 'colB01022', 'colB01025', 'colB01028', 'colB01031'], calcType: 'sum', message: '严重损坏住房总户数 = 各结构户数之和' },
      { targetField: 'colB01035', sourceFields: ['colB01020', 'colB01023', 'colB01026', 'colB01029', 'colB01032'], calcType: 'sum', message: '严重损坏住房总间数 = 各结构间数之和' },
      { targetField: 'colB01036', sourceFields: ['colB01021', 'colB01024', 'colB01027', 'colB01030', 'colB01033'], calcType: 'sum', message: '严重损坏住房经济损失 = 各结构经济损失之和' },
      { targetField: 'colB01052', sourceFields: ['colB01037', 'colB01040', 'colB01043', 'colB01046', 'colB01049'], calcType: 'sum', message: '一般损坏住房总户数 = 各结构户数之和' },
      { targetField: 'colB01053', sourceFields: ['colB01038', 'colB01041', 'colB01044', 'colB01047', 'colB01050'], calcType: 'sum', message: '一般损坏住房总间数 = 各结构间数之和' },
      { targetField: 'colB01054', sourceFields: ['colB01039', 'colB01042', 'colB01045', 'colB01048', 'colB01051'], calcType: 'sum', message: '一般损坏住房经济损失 = 各结构经济损失之和' },
      { targetField: 'colB01055', sourceFields: ['colB01018', 'colB01036', 'colB01054'], calcType: 'sum', message: '农村居民住房经济损失合计 = 倒塌 + 严重损坏 + 一般损坏' }
    ]
  },

  // ==================== B02 城镇居民住房受损情况统计表 ====================
  B02: {
    autoCalcRules: [
      { targetField: 'colB02021', sourceFields: ['colB02001', 'colB02005', 'colB02009', 'colB02013', 'colB02017'], calcType: 'sum', message: '倒塌住房总户数 = 各结构户数之和' },
      { targetField: 'colB02022', sourceFields: ['colB02002', 'colB02006', 'colB02010', 'colB02014', 'colB02018'], calcType: 'sum', message: '倒塌住房总间数 = 各结构间数之和' },
      { targetField: 'colB02023', sourceFields: ['colB02003', 'colB02007', 'colB02011', 'colB02015', 'colB02019'], calcType: 'sum', message: '倒塌住房总面积 = 各结构面积之和' },
      { targetField: 'colB02024', sourceFields: ['colB02004', 'colB02008', 'colB02012', 'colB02016', 'colB02020'], calcType: 'sum', message: '倒塌住房经济损失 = 各结构经济损失之和' },
      { targetField: 'colB02045', sourceFields: ['colB02025', 'colB02029', 'colB02033', 'colB02037', 'colB02041'], calcType: 'sum', message: '严重损坏住房总户数 = 各结构户数之和' },
      { targetField: 'colB02046', sourceFields: ['colB02026', 'colB02030', 'colB02034', 'colB02038', 'colB02042'], calcType: 'sum', message: '严重损坏住房总间数 = 各结构间数之和' },
      { targetField: 'colB02047', sourceFields: ['colB02027', 'colB02031', 'colB02035', 'colB02039', 'colB02043'], calcType: 'sum', message: '严重损坏住房总面积 = 各结构面积之和' },
      { targetField: 'colB02048', sourceFields: ['colB02028', 'colB02032', 'colB02036', 'colB02040', 'colB02044'], calcType: 'sum', message: '严重损坏住房经济损失 = 各结构经济损失之和' },
      { targetField: 'colB02069', sourceFields: ['colB02049', 'colB02053', 'colB02057', 'colB02061', 'colB02065'], calcType: 'sum', message: '一般损坏住房总户数 = 各结构户数之和' },
      { targetField: 'colB02070', sourceFields: ['colB02050', 'colB02054', 'colB02058', 'colB02062', 'colB02066'], calcType: 'sum', message: '一般损坏住房总间数 = 各结构间数之和' },
      { targetField: 'colB02071', sourceFields: ['colB02051', 'colB02055', 'colB02059', 'colB02063', 'colB02067'], calcType: 'sum', message: '一般损坏住房总面积 = 各结构面积之和' },
      { targetField: 'colB02072', sourceFields: ['colB02052', 'colB02056', 'colB02060', 'colB02064', 'colB02068'], calcType: 'sum', message: '一般损坏住房经济损失 = 各结构经济损失之和' },
      { targetField: 'colB02073', sourceFields: ['colB02024', 'colB02048', 'colB02072'], calcType: 'sum', message: '城镇居民住房经济损失合计 = 倒塌 + 严重损坏 + 一般损坏' }
    ]
  },

  // ==================== C01 居民家庭财产损失统计表 ====================
  C01: {
    autoCalcRules: [
      { targetField: 'colC01005', sourceFields: ['colC01002', 'colC01003', 'colC01004'], calcType: 'sum', message: '农村家庭财产损失小计 = 固定资产 + 耐用消费品 + 其他' },
      { targetField: 'colC01010', sourceFields: ['colC01007', 'colC01008', 'colC01009'], calcType: 'sum', message: '城镇家庭财产损失小计 = 固定资产 + 耐用消费品 + 其他' },
      { targetField: 'colC01011', sourceFields: ['colC01005', 'colC01010'], calcType: 'sum', message: '居民家庭财产损失合计 = 农村 + 城镇' }
    ]
  },

  // ==================== D01 农林牧渔业损失统计表 ====================
  D01: {
    autoCalcRules: [
      { targetField: 'colD01042', sourceFields: ['colD01007', 'colD01020', 'colD01029', 'colD01037', 'colD01039'], calcType: 'sum', message: '农林牧渔业经济损失合计 = 农业 + 林业 + 畜牧业 + 渔业 + 农业机械' }
    ],
    compareRules: [
      { field: 'colD01001', operator: '>=', compareField: 'colD01002', errorMessage: '农作物受灾面积不能小于成灾面积' },
      { field: 'colD01002', operator: '>=', compareField: 'colD01003', errorMessage: '农作物成灾面积不能小于绝收面积' },
      { field: 'colD01007', operator: '>=', compareFields: ['colD01004', 'colD01006'], errorMessage: '农业经济损失应大于等于农作物经济损失与农业生产大棚经济损失之和' },
      { field: 'colD01020', operator: '>=', compareFields: ['colD01010', 'colD01012', 'colD01014', 'colD01016', 'colD01018', 'colD01019'], errorMessage: '林业经济损失应大于等于各项分项经济损失之和' },
      { field: 'colD01029', operator: '>=', compareFields: ['colD01024', 'colD01026', 'colD01028'], errorMessage: '畜牧业经济损失应大于等于各项分项经济损失之和' },
      { field: 'colD01037', operator: '>=', compareFields: ['colD01032', 'colD01033', 'colD01034', 'colD01036'], errorMessage: '渔业经济损失应大于等于各项分项经济损失之和' }
    ]
  },

  // ==================== E01 工业损失统计表 ====================
  E01: {
    autoCalcRules: [
      { targetField: 'colE01015', sourceFields: ['colE01007', 'colE01014'], calcType: 'sum', message: '工业经济损失合计 = 规模以上 + 规模以下' }
    ],
    compareRules: [
      { field: 'colE01007', operator: '>=', compareFields: ['colE01003', 'colE01005', 'colE01006'], errorMessage: '规模以上工业经济损失应大于等于各项分项经济损失之和' },
      { field: 'colE01014', operator: '>=', compareFields: ['colE01010', 'colE01012', 'colE01013'], errorMessage: '规模以下工业经济损失应大于等于各项分项经济损失之和' }
    ]
  },

  // ==================== F01 服务业损失统计表 ====================
  F01: {
    autoCalcRules: [
      { targetField: 'colF01026', sourceFields: ['colF01005', 'colF01009', 'colF01013', 'colF01017', 'colF01018', 'colF01023'], calcType: 'sum', message: '服务业经济损失合计 = 各行业损失之和' }
    ],
    compareRules: [
      { field: 'colF01005', operator: '>=', compareFields: ['colF01003', 'colF01004'], errorMessage: '批发和零售业经济损失应大于等于各项分项经济损失之和' },
      { field: 'colF01009', operator: '>=', compareField: 'colF01008', errorMessage: '住宿和餐饮业经济损失应大于等于设备设施损失' },
      { field: 'colF01013', operator: '>=', compareField: 'colF01012', errorMessage: '金融业经济损失应大于等于设备设施损失' },
      { field: 'colF01017', operator: '>=', compareField: 'colF01016', errorMessage: '文化、体育和娱乐业经济损失应大于等于设备设施损失' },
      { field: 'colF01023', operator: '>=', compareFields: ['colF01021', 'colF01022'], errorMessage: '其他服务业经济损失应大于等于各项分项经济损失之和' }
    ]
  },

  // ==================== G01 基础设施(交通运输)损失统计表 ====================
  G01: {
    autoCalcRules: [
      { targetField: 'colG01062', sourceFields: ['colG01059', 'colG01061'], calcType: 'sum', message: '客（货）运站经济损失 = 客运站 + 货运站' },
      { targetField: 'colG01100', sourceFields: ['colG01031', 'colG01070', 'colG01077', 'colG01082', 'colG01097'], calcType: 'sum', message: '基础设施(交通运输)经济损失合计 = 公路+铁路+水运+航空+城市交通' }
    ],
    compareRules: [
      // 公路部分
      { field: 'colG01011', operator: '>=', compareFields: ['colG01002', 'colG01004', 'colG01006', 'colG01008', 'colG01010'], errorMessage: '国省干线经济损失应大于等于各项分项损失之和' },
      { field: 'colG01022', operator: '>=', compareFields: ['colG01013', 'colG01015', 'colG01017', 'colG01019', 'colG01021'], errorMessage: '其他公路经济损失应大于等于各项分项损失之和' },
      { field: 'colG01023', operator: '>=', compareField: 'colG01024', errorMessage: '受损客运站数量不能小于受损等级站数量' },
      { field: 'colG01030', operator: '>=', compareFields: ['colG01025', 'colG01027', 'colG01029'], errorMessage: '客（货）运站、服务区经济损失应大于等于各项分项损失之和' },
      { field: 'colG01031', operator: '>=', compareFields: ['colG01011', 'colG01022', 'colG01030'], errorMessage: '公路经济损失小计应大于等于各分项之和' },
      // 铁路部分
      { field: 'colG01044', operator: '>=', compareFields: ['colG01033', 'colG01035', 'colG01037', 'colG01039', 'colG01041', 'colG01042', 'colG01043'], errorMessage: '高速铁路经济损失应大于等于各项分项损失之和' },
      { field: 'colG01057', operator: '>=', compareFields: ['colG01046', 'colG01048', 'colG01050', 'colG01052', 'colG01054', 'colG01055', 'colG01056'], errorMessage: '普通铁路经济损失应大于等于各项分项损失之和' },
      { field: 'colG01069', operator: '>=', compareFields: ['colG01064', 'colG01066', 'colG01068'], errorMessage: '运输工具经济损失应大于等于各项分项损失之和' },
      { field: 'colG01070', operator: '>=', compareFields: ['colG01044', 'colG01057', 'colG01062', 'colG01069'], errorMessage: '铁路经济损失小计应大于等于各分项之和' },
      // 水运部分
      { field: 'colG01077', operator: '>=', compareFields: ['colG01072', 'colG01074', 'colG01076'], errorMessage: '水运经济损失应大于等于各项分项损失之和' },
      // 航空部分
      { field: 'colG01082', operator: '>=', compareFields: ['colG01079', 'colG01081'], errorMessage: '航空经济损失应大于等于各项分项损失之和' },
      // 城市交通部分
      { field: 'colG01097', operator: '>=', compareFields: ['colG01084', 'colG01086', 'colG01088', 'colG01090', 'colG01092', 'colG01094', 'colG01096'], errorMessage: '城市交通运输基础设施经济损失应大于等于各项分项损失之和' }
    ]
  },

  // ==================== G02 基础设施(通信)损失统计表 ====================
  G02: {
    autoCalcRules: [
      { targetField: 'colG02012', sourceFields: ['colG02003', 'colG02008', 'colG02011'], calcType: 'sum', message: '基础设施(通信)经济损失合计 = 通信+邮政+其他' }
    ],
    compareRules: [
      { field: 'colG02008', operator: '>=', compareField: 'colG02005', errorMessage: '邮政经济损失应大于等于邮政设备设施损失' }
    ]
  },

  // ==================== G03 基础设施(能源)损失统计表 ====================
  G03: {
    autoCalcRules: [
      { targetField: 'colG03041', sourceFields: ['colG03020', 'colG03038'], calcType: 'sum', message: '基础设施(能源)经济损失合计 = 电力 + 煤油气' }
    ],
    compareRules: [
      { field: 'colG03020', operator: '>=', compareFields: ['colG03003', 'colG03006', 'colG03009', 'colG03012', 'colG03015', 'colG03019'], errorMessage: '电力经济损失应大于等于各项分项损失之和' },
      { field: 'colG03038', operator: '>=', compareFields: ['colG03023', 'colG03025', 'colG03027', 'colG03029', 'colG03031', 'colG03033', 'colG03035', 'colG03037'], errorMessage: '煤油气经济损失应大于等于各项分项损失之和' }
    ]
  },

  // ==================== G04 基础设施(水利)损失统计表 ====================
  G04: {
    autoCalcRules: [
      { targetField: 'colG04024', sourceFields: ['colG04022', 'colG04023'], calcType: 'sum', message: '基础设施(水利)经济损失合计 = 防洪排灌设施 + 其他水利工程' }
    ],
    compareRules: [
      { field: 'colG04022', operator: '>=', compareFields: ['colG04002', 'colG04004', 'colG04007', 'colG04009', 'colG04011', 'colG04013', 'colG04015', 'colG04017', 'colG04019', 'colG04021'], errorMessage: '防洪排灌设施经济损失应大于等于各项分项损失之和' },
      { field: 'colG04024', operator: '>=', compareFields: ['colG04022', 'colG04023'], errorMessage: '基础设施(水利)经济损失合计应大于等于各项分项损失之和' }
    ]
  },

  // ==================== G05 基础设施(市政)损失统计表 ====================
  G05: {
    autoCalcRules: [
      { targetField: 'colG05042', sourceFields: ['colG05007', 'colG05012', 'colG05021', 'colG05030', 'colG05035', 'colG05037', 'colG05038', 'colG05039'], calcType: 'sum', message: '基础设施(市政)经济损失合计 = 各系统损失之和' }
    ],
    compareRules: [
      { field: 'colG05007', operator: '>=', compareFields: ['colG05002', 'colG05004', 'colG05006'], errorMessage: '供水系统经济损失应大于等于各项分项损失之和' },
      { field: 'colG05012', operator: '>=', compareFields: ['colG05009', 'colG05011'], errorMessage: '排水系统经济损失应大于等于各项分项损失之和' },
      { field: 'colG05021', operator: '>=', compareFields: ['colG05014', 'colG05016', 'colG05018', 'colG05020'], errorMessage: '燃气系统经济损失应大于等于各项分项损失之和' },
      { field: 'colG05030', operator: '>=', compareFields: ['colG05023', 'colG05025', 'colG05027', 'colG05029'], errorMessage: '供热系统经济损失应大于等于各项分项损失之和' },
      { field: 'colG05035', operator: '>=', compareFields: ['colG05032', 'colG05034'], errorMessage: '城市公共交通系统经济损失应大于等于各项分项损失之和' }
    ]
  },

  // ==================== G06 基础设施(农村地区生活设施)损失统计表 ====================
  G06: {
    autoCalcRules: [
      { targetField: 'colG06011', sourceFields: ['colG06001', 'colG06002', 'colG06003', 'colG06004', 'colG06005', 'colG06006', 'colG06007', 'colG06008', 'colG06010'], calcType: 'sum', message: '基础设施(农村地区生活设施)经济损失合计 = 各项损失之和' }
    ]
  },

  // ==================== G07 基础设施(地质灾害防治设施)损失统计表 ====================
  G07: {
    autoCalcRules: [
      { targetField: 'colG07017', sourceFields: ['colG07002', 'colG07004', 'colG07006', 'colG07008', 'colG07010', 'colG07012', 'colG07014', 'colG07016'], calcType: 'sum', message: '基础设施(地质灾害防治设施)经济损失合计 = 各类型设施损失之和' }
    ]
  },

  // ==================== H01 公共服务(教育系统)损失统计表 ====================
  H01: {
    autoCalcRules: [
      { targetField: 'colH01015', sourceFields: ['colH01002', 'colH01004', 'colH01006', 'colH01008', 'colH01010', 'colH01012', 'colH01014'], calcType: 'sum', message: '公共服务(教育系统)经济损失合计 = 各级学校损失 + 校舍损失' }
    ]
  },

  // ==================== H02 公共服务(科技系统)损失统计表 ====================
  H02: {
    autoCalcRules: [
      { targetField: 'colH02034', sourceFields: ['colH02005', 'colH02030', 'colH02031'], calcType: 'sum', message: '公共服务(科技系统)经济损失合计 = 研究试验 + 专业监测 + 其他' }
    ],
    compareRules: [
      { field: 'colH02005', operator: '>=', compareField: 'colH02004', errorMessage: '研究试验机构经济损失应大于等于试验设备损失' },
      { field: 'colH02030', operator: '>=', compareFields: ['colH02008', 'colH02011', 'colH02014', 'colH02017', 'colH02020', 'colH02023', 'colH02026', 'colH02029'], errorMessage: '专业监测经济损失应大于等于各项分项损失之和' }
    ]
  },

  // ==================== H03 公共服务(医疗卫生系统)损失统计表 ====================
  H03: {
    autoCalcRules: [
      { targetField: 'colH03009', sourceFields: ['colH03002', 'colH03004', 'colH03006', 'colH03008'], calcType: 'sum', message: '医疗卫生机构经济损失小计 = 各类机构损失之和' },
      { targetField: 'colH03015', sourceFields: ['colH03009', 'colH03011', 'colH03012'], calcType: 'sum', message: '公共服务(医疗卫生系统)经济损失合计 = 医疗机构 + 食药监 + 其他' }
    ]
  },

  // ==================== H04 公共服务(文化系统)损失统计表 ====================
  H04: {
    autoCalcRules: [
      { targetField: 'colH04018', sourceFields: ['colH04002', 'colH04004', 'colH04006', 'colH04008', 'colH04010', 'colH04012', 'colH04014', 'colH04015'], calcType: 'sum', message: '公共服务(文化系统)经济损失合计 = 各文化机构损失之和' }
    ]
  },

  // ==================== H05 公共服务(广播电视系统)损失统计表 ====================
  H05: {
    autoCalcRules: [
      { targetField: 'colH05014', sourceFields: ['colH05002', 'colH05004', 'colH05005', 'colH05006', 'colH05007', 'colH05008', 'colH05009', 'colH05011', 'colH05013'], calcType: 'sum', message: '公共服务(广播电视系统)经济损失合计 = 各项损失之和' }
    ]
  },

  // ==================== H06 公共服务(新闻出版系统)损失统计表 ====================
  H06: {
    autoCalcRules: [
      { targetField: 'colH06010', sourceFields: ['colH06002', 'colH06004', 'colH06006', 'colH06007'], calcType: 'sum', message: '公共服务(新闻出版系统)经济损失合计 = 各项损失之和' }
    ]
  },

  // ==================== H07 公共服务(体育)损失统计表 ====================
  H07: {
    autoCalcRules: [
      { targetField: 'colH07004', sourceFields: ['colH07001', 'colH07002'], calcType: 'sum', message: '体育机构数量小计 = 体育运动学校 + 体育训练基地' },
      { targetField: 'colH07018', sourceFields: ['colH07009', 'colH07012', 'colH07015'], calcType: 'sum', message: '体育场馆数量小计 = 体育场 + 体育馆 + 游泳馆' },
      { targetField: 'colH07019', sourceFields: ['colH07010', 'colH07013', 'colH07016'], calcType: 'sum', message: '体育场馆座席数量小计 = 体育场 + 体育馆 + 游泳馆' },
      { targetField: 'colH07020', sourceFields: ['colH07011', 'colH07014', 'colH07017'], calcType: 'sum', message: '体育场馆经济损失小计 = 体育场 + 体育馆 + 游泳馆' },
      { targetField: 'colH07023', sourceFields: ['colH07003', 'colH07006', 'colH07008', 'colH07020'], calcType: 'sum', message: '公共服务(体育)经济损失合计 = 机构 + 场地 + 器材 + 场馆' }
    ]
  },

  // ==================== H08 公共服务(社会保障与社会服务系统)损失统计表 ====================
  H08: {
    autoCalcRules: [
      { targetField: 'colH08007', sourceFields: ['colH08002', 'colH08004', 'colH08006'], calcType: 'sum', message: '社会保障系统经济损失小计 = 各级保障机构损失之和' },
      { targetField: 'colH08034', sourceFields: ['colH08009', 'colH08011', 'colH08013', 'colH08015', 'colH08017', 'colH08019', 'colH08021', 'colH08023', 'colH08025', 'colH08027', 'colH08029', 'colH08031', 'colH08033'], calcType: 'sum', message: '社会服务系统经济损失小计 = 各机构损失之和' },
      { targetField: 'colH08037', sourceFields: ['colH08007', 'colH08034'], calcType: 'sum', message: '公共服务(社会保障与社会服务系统)经济损失合计 = 社会保障 + 社会服务' }
    ]
  },

  // ==================== H09 公共服务(公安系统和国家综合性消防救援队伍)损失统计表 ====================
  H09: {
    autoCalcRules: [
      { targetField: 'colH09009', sourceFields: ['colH09002', 'colH09004', 'colH09006', 'colH09008'], calcType: 'sum', message: '公安系统经济损失小计 = 各机构损失之和' },
      { targetField: 'colH09024', sourceFields: ['colH09011', 'colH09013', 'colH09015', 'colH09017', 'colH09019', 'colH09021', 'colH09023'], calcType: 'sum', message: '消防救援队伍经济损失小计 = 各机构损失之和' },
      { targetField: 'colH09027', sourceFields: ['colH09009', 'colH09024'], calcType: 'sum', message: '公共服务(公安系统和国家综合性消防救援队伍)经济损失合计 = 公安 + 消防救援' }
    ]
  },

  // ==================== H10 公共服务(社会管理系统)损失统计表 ====================
  H10: {
    autoCalcRules: [
      { targetField: 'colH10012', sourceFields: ['colH10002', 'colH10004', 'colH10006', 'colH10008', 'colH10009'], calcType: 'sum', message: '公共服务(社会管理系统)经济损失合计 = 各机构损失之和' }
    ]
  },

  // ==================== I01 资源与环境损失统计表 ====================
  I01: {
    compareRules: [
      { field: 'colI01008', operator: '>=', compareField: 'colI01009', errorMessage: '野生动物伤亡数不能小于国家重点保护野生动物伤亡数' }
    ]
  },

  // ==================== J01 基础指标统计表 ====================
  J01: {
    autoCalcRules: [
      { targetField: 'colJ01001', sourceFields: ['colJ01002', 'colJ01003'], calcType: 'sum', message: '总人口 = 城镇人口 + 农村人口' }
    ],
    compareRules: [
      { field: 'colJ01001', operator: '>', compareField: 'colJ01004', errorMessage: '总人口应大于女性人口' },
      { field: 'colJ01001', operator: '>', compareField: 'colJ01005', errorMessage: '总人口应大于18岁以下人口' },
      { field: 'colJ01001', operator: '>', compareField: 'colJ01006', errorMessage: '总人口应大于60岁及以上人口' },
      { field: 'colJ01007', operator: '>=', compareField: 'colJ01008', errorMessage: '总户数应大于等于城镇户数' },
      { field: 'colJ01070', operator: '>=', compareField: 'colJ01071', errorMessage: '公路里程应大于等于高速公路里程' }
    ]
  },

  // ==================== Z01 经济损失统计汇总表 ====================
  Z01: {
    autoCalcRules: [
      {
        targetField: 'colZ01024',
        sourceFields: [
          'colZ01001', 'colZ01002', 'colZ01003', 'colZ01004', 'colZ01005',
          'colZ01006', 'colZ01007', 'colZ01008', 'colZ01009', 'colZ01010',
          'colZ01011', 'colZ01012', 'colZ01013', 'colZ01014', 'colZ01015',
          'colZ01016', 'colZ01017', 'colZ01018', 'colZ01019', 'colZ01020',
          'colZ01021', 'colZ01022', 'colZ01023'
        ],
        calcType: 'sum',
        message: '经济损失合计 = 各项经济损失之和'
      }
    ]
  }
}

/**
 * 根据表格代码获取验证配置
 */
export function getTableConfig(tableCode: string): TableValidationConfig | undefined {
  return tableValidationConfig[tableCode]
}

/**
 * 获取自动计算字段列表
 */
export function getAutoCalcFields(tableCode: string): string[] {
  const config = getTableConfig(tableCode)
  return config?.autoCalcRules?.map(rule => rule.targetField) || []
}

/**
 * 检查字段是否为自动计算字段
 */
export function isAutoCalcField(tableCode: string, field: string): boolean {
  return getAutoCalcFields(tableCode).includes(field)
}
