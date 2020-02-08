<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav   class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button class="navbar-toggle" data-toggle="collapse" type="button">
                <span class="sr-only">Toggle navigation</span><span
                    class="icon-bar"></span><span class="icon-bar"></span><span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${context}/">${title}</a>
        </div>
        <div class="collapse navbar-collapse">

            <ul class="nav navbar-nav navbar-right">
                <li><a href="${context}/logout"><span>Log out</span></a></li>
                <li class="separator hidden-lg hidden-md"></li>
            </ul>
        </div>
    </div>
</nav>
