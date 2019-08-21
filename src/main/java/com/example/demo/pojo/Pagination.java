package com.example.demo.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/*封装每页问题列表的展示，用于分页的实现*/
@Data
public class Pagination {
    private List<QuestionDTO> questionDTOS;
    private int page;//当前页面
    private int size;//每页显示的最大数
    private boolean showFirst;
    private boolean showEnd;
    private boolean showPrevious;
    private boolean showNext;
    private List<Integer> pages;//分页组件中显示的分页编号
    private int endPage;

    //分页组件最多包含五个分页编号，当前页的前两页和后两页
    public void setPaginaton(int totalPages)
    {
        pages=new ArrayList<Integer>();
        pages.add(page);
        for(int i=1;i<=2;i++)
        {
            if(page-i>0)
                pages.add(0,page-i);
            if(page+i<=totalPages)
                pages.add(page+i);
        }
        //控制上一页下一页及首页和尾页的显示
        showEnd=true;showFirst=true;showPrevious=true;showNext=true;
        if(page==1)
        {
            showPrevious=false;
            showFirst=false;
        }
        if (page==totalPages)
        {
            showNext=false;
            showNext=false;
        }
        if(pages.contains(1))
            showFirst=false;
        if(pages.contains(totalPages))
            showEnd=false;
    }


}
