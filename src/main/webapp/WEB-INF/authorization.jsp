<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
    <link rel="stylesheet" href="/css/authorization.css">
</head>
<body>
<div class="form">
    <%--    <% if (session.getAttribute("msg") != null) {%>--%>
    <%--    <span style="color: red"><%=session.getAttribute("msg")%></span>--%>
    <%--    <% session.removeAttribute("msg");%>--%>
    <%--    <%}%>--%>
    <%--    <span id="errorMessage" style="color: red"></span>--%>
    <ul class="tab-group">
        <li class="tab active"><a href="#signup">Sign Up</a></li>
        <li class="tab"><a href="#login">Log In</a></li>
    </ul>

    <div class="tab-content">

        <div id="signup">
            <% if (session.getAttribute("msg") != null) {%>
            <h1><%=session.getAttribute("msg")%>
            </h1>
            <% session.removeAttribute("msg");%>
            <%} else {%>
            <h1>Sign Up for Free</h1>
            <%}%>

            <form action="/register" method="post">

                <div class="top-row">
                    <div class="field-wrap">
                        <label>
                            First Name<span class="req">*</span>
                        </label>
                        <input type="text" required autocomplete="off" name="name"/>
                    </div>

                    <div class="field-wrap">
                        <label>
                            Last Name<span class="req">*</span>
                        </label>
                        <input type="text" required autocomplete="off" name="surname"/>
                    </div>
                </div>

                <div class="field-wrap">
                    <label>
                        Email Address<span class="req">*</span>
                    </label>
                    <input type="email" required autocomplete="off" name="email"/>
                </div>

                <div class="field-wrap">
                    <label>
                        Set A Password<span class="req">*</span>
                    </label>
                    <input type="password" required autocomplete="off" name="password"/>
                </div>

                <button type="submit" class="button button-block"/>
                Get Started</button>

            </form>

        </div>

        <div id="login">
            <h1>Welcome Back!</h1>

            <form action="/login" method="post">

                <div class="field-wrap">
                    <label>
                        Email Address<span class="req">*</span>
                    </label>
                    <input type="email" required autocomplete="off" name="email" id="loginEmail"/>
                </div>

                <div class="field-wrap">
                    <label>
                        Password<span class="req">*</span>
                    </label>
                    <input type="password" required autocomplete="off" name="password" id="loginPass"/>
                </div>

                <button type="submit" id="loginBtn" class="button button-block"/>
                Log In</button>

            </form>

        </div>

    </div><!-- tab-content -->

</div> <!-- /form -->
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="/js/authorization.js"></script>
</body>
</html>
