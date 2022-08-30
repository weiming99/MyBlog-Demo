package chen.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chen
 * @crete 2022-08-29-21:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO {

    //响应给前端的状态码
    private int code;

    //响应给前端的提示信息
    private String msg;

    //响应给前端的数据
    private Object data;

}