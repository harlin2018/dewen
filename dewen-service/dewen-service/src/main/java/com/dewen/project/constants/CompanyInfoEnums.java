package com.dewen.project.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/7/14 14:52
 */
public class CompanyInfoEnums {

    @Getter
    @AllArgsConstructor
    public enum WasteType {
        wasteWater("废水", "1"),
        wasteGas("废气", "2");

        String cnLabel;
        String value;
    }

    @Getter
    @AllArgsConstructor
    public enum RecordType {
        inspectRecord("巡查执法记录", "1"),
        adminRecord("行政执法记录", "2");

        String cnLabel;
        String value;
    }


}
