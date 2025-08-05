package com.ectransport.platform.domain.core.constans;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class StatusConstans {
  public static final Integer CREATED = 1 ;
  public static final Integer ASSIGNED = 2 ;
  public static final Integer DONE = 3 ;
  public static final Integer FINALIZED = 4 ;
  public static final Integer LIQUIDATED = 5 ;
  public static final Integer DIALIQUI = 6 ;


}
