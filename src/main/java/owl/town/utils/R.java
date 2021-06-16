package owl.town.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 统一返回结果类
 *
 * @author 郑人滏
 * @version 1.0
 * @since 2021年6月16日00:05:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class R implements Serializable {

    // 状态响应码，200代表正常，400代表未找到，500代表错误请求及无返回
    private Integer code;

    // 返回的数据
    private Object data;

    // 返回的消息
    private String message;

    public R ok() {
        this.code = 200;
        this.data = "";
        this.message = "";
        return this;
    }

    public R ok(Object data) {
        this.code = 200;
        this.data = data;
        this.message = "";
        return this;
    }

    public R ok(String message) {
        this.code = 200;
        this.data = "";
        this.message = message;
        return this;
    }

    public R ok(Object data, String message) {
        this.code = 200;
        this.data = data;
        this.message = message;
        return this;
    }

    public R fail(String message) {
        this.code = 500;
        this.data = "";
        this.message = message;
        return this;
    }

    public R fail() {
        this.code = 500;
        this.data = "";
        this.message = "";
        return this;
    }

}
