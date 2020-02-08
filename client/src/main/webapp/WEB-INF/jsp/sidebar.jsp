<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar" data-color="red" data-image="">
    <div class="sidebar-wrapper ps">
        <div class="logo">

            <a class="simple-text" href="${context}/">
                <div class="logo-img"><img src="${context}/img/angular-red.png"></div> Person Assignment
            </a>
        </div>
        <ul class="nav responsive-nav">
            <li><a href="${context}/"><i class="pe-7s-graph"></i> <span>Dashboard</span></a></li>
            <li><a href="${context}/person/list"><i class="pe-7s-note2"></i><span>Person</span></a></li>
        </ul>
    </div>
    <div class="sidebar-background"  style="background-image: url( '${context}/img/sidebar-5.jpg' )"></div>
</div>
