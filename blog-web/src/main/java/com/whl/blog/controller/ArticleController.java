package com.whl.blog.controller;

import com.urwoo.entity.ArticleCommentInfo;
import com.urwoo.entity.ArticleInfo;
import com.urwoo.entity.PageBean;
import com.urwoo.enums.Status;
import com.urwoo.request.ArticleCommentQueryReq;
import com.urwoo.service.ArticleCommentService;
import com.urwoo.service.ArticleService;
import com.urwoo.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/article/")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleCommentService articleCommentService;

    @RequestMapping("get/{id}")
    public ModelAndView details(@PathVariable("id") Long id,
                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                HttpServletRequest request) throws Exception {
        ModelAndView mav=new ModelAndView();
        ArticleInfo articleInfo = articleService.getInfo(id);
        mav.addObject("articleInfo", articleInfo);

        PageBean pageBean = new PageBean(page, limit);

        ArticleCommentQueryReq req = new ArticleCommentQueryReq();
        req.setArticleId(id);
        req.setStatus(Status.ON.code());
        List<ArticleCommentInfo> commentList = articleCommentService
                .comments(req, pageBean.getStart(), limit);
        long count = articleCommentService.count(req);

        // 拼接url
        String targetUrl = request.getContextPath() + "/article/get/"+id+".html";
        StringBuffer param = new StringBuffer(); // 查询参数
        mav.addObject("commentList", commentList); // 查询所有评论信息
        mav.addObject("pageCode", PageUtil.genPagination(targetUrl ,
                count, page, limit, param.toString()));
        mav.addObject("page", pageBean); //分页信息
        mav.addObject("mainPage", "foreground/article/comment.jsp");
        mav.setViewName("mainTemp");
        return mav;
    }
}
