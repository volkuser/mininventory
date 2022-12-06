<#import "parts/common.ftl" as c>

<@c.page>
    <h3>Employee control</h3>
    <div class="d-flex flex-row mb-3">
        <div class="p-2">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Surname</th>
                        <th scope="col">User (email)</th>
                    </tr>
                </thead>
                <tbody>
                    <#list employees as employee>
                        <tr>
                            <td>${employee.id}</td>
                            <td>${employee.surname}</td>
                            <td><#if employee.user??>${employee.user.email}<#else>empty</#if></td>
                            <td><a href="employee/more/${employee.id}">More</a></td>
                        </tr>
                    <#else>
                        <td>no employees</td>
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
                        <button type="submit" class="btn btn-primary" formaction="/employee/exact"
                                formmethod="get">Exact search</button>
                    </div>
                    <div class="p-1">
                        <button type="submit" class="btn btn-primary" formaction="/employee/imprecise"
                                formmethod="get">Imprecise search</button>
                    </div>
                </form>
            </div>
            <div class="p-2">
                <h5>Add new employee</h5>
                <form method="post">
                    <div class="mb-3 d-flex flex-row align-items-center">
                        <label class="form-label" for="surname_id">Surname</label>
                        <div class="p-2 d-flex flex-row">
                            <div class="d-flex flex-row">
                                <input id="surname_id" type="text" name="surname"
                                       class="form-control ${(surnameError??)?string('is-invalid', '')} p-2">
                                <#if surnameError??>
                                    <div class="invalid-feedback p-2">
                                        ${surnameError}
                                    </div>
                                </#if>
                            </div>
                        </div>
                    </div>
                    <div><input type="hidden" name="_csrf" value="${_csrf.token}"></div>
                    <button type="submit" class="btn btn-primary">Add</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>