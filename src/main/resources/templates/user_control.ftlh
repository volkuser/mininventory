<#--
<#import "parts/common.ftl" as c>

<@c.page>
    <h3>User control</h3>
    <div class="d-flex flex-row mb-3">
        <div class="p-2">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Email</th>
                        <th scope="col">Employee (surname)</th>
                    </tr>
                </thead>
                <tbody>
                    <#list users as user>
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.email}</td>
                            <td><#if user.employee??>${user.employee.surname}<#else>empty</#if></td>
                            <td><a href="user/more/${user.id}">More</a></td>
                        </tr>
                    <#else>
                        <td>no users</td>
                    </#list>
                </tbody>
            </table>
        </div>
        <div class="d-flex flex-column mb-3">
            <div class="p-2">
                <form method="get">
                    <h5>Search</h5>
                    <div class="mb-3">
                        <label>
                            <input type="text" class="form-control" name="query"/>
                        </label>
                    </div>
                    <div class="p-1">
                        <button type="submit" class="btn btn-primary" formaction="/user/exact"
                                formmethod="get">Exact search</button>
                    </div>
                    <div class="p-1">
                        <button type="submit" class="btn btn-primary" formaction="/user/imprecise"
                                formmethod="get">Imprecise search</button>
                    </div>
                </form>
            </div>
            <div class="p-2">
                <h5>Add new user</h5>
                <form method="post">
                    <div class="mb-3 d-flex flex-row align-items-center">
                        <label class="form-label" for="email_id">Email</label>
                        <div class="p-2 d-flex flex-row">
                            <div class="d-flex flex-row">
                                <input id="email_id" type="text" name="email"
                                       class="form-control ${(emailError??)?string('is-invalid', '')} p-2">
                                <#if emailError??>
                                    <div class="invalid-feedback p-2">
                                        ${emailError}
                                    </div>
                                </#if>
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Employee</label>
                        <label>
                            <select name="employee" class="form-control">
                                <#list employees as employee>
                                    <option value="${employee.id}">${employee.surname}</option>
                                </#list>
                            </select>
                        </label>
                    </div>
                    <button type="submit" class="btn btn-primary">Add</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>-->
