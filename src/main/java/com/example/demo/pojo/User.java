package com.example.demo.pojo;

import lombok.Data;

@Data  //@ToString，@EqualsAndHashCode， @Getter，@Setter， @RequiredArgsConstructor，自当生成这些get、set等方法
public class User {
    private int id;
    private String name,
            account_id,
    avatar_url,
    bio,
    token;
    private Long gmt_create, gmt_modified;

}
