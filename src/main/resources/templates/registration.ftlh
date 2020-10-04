<#import "fragments/error-login.ftl" as error/>
<!doctype html>
<html lang="en">
<head>
    <#include "fragments/bootsrap-links.ftl"/>

    <title>Login</title>
</head>
<body>

<#include "fragments/nav-bar-log-reg.ftl" />
<br>
<main class="registration-form">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Register</div>
                    <div class="card-body">
                        <@error.error_login/>
                        <form action="/api/candidate/registration" method="POST">
                            <div class="form-group row">
                                <label for="username" class="col-md-4 col-form-label text-md-right">Username </label>
                                <div class="col-md-6">
                                    <input type="text" id="username" class="form-control" name="username"
                                           required autofocus>
                                </div>
                            </div>

<#--                            <div class="form-group row">-->
<#--                                <label for="email_address" class="col-md-4 col-form-label text-md-right">E-Mail-->
<#--                                    Address</label>-->
<#--                                <div class="col-md-6">-->
<#--                                    <input type="email" id="email_address" class="form-control" name="email" required>-->
<#--                                </div>-->
<#--                            </div>-->

                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                                <div class="col-md-6">
                                    <input type="password" id="password" class="form-control" name="password" required>
                                </div>
                            </div>


                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    Register
                                </button>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>


</body>
</html>

</body>
</html>