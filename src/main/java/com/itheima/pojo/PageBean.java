package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: PageBean
 * Package: com.itheima.pojo
 * Description:
 *
 * @Author dhf
 * @Create 2023/7/18 16:09
 * @Version 17.0.6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
    private Long total;
    private List rows;
}
