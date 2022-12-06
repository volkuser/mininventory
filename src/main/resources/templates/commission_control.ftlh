<#import "parts/common.ftl" as c>

<@c.page>
    <h3>Commission control</h3>
    <div class="d-flex flex-row mb-3">
        <div class="p-2">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Number</th>
                        <th scope="col">Event date</th>
                    </tr>
                </thead>
                <tbody>
                    <#list commissions as commission>
                        <tr>
                            <td>${commission.id}</td>
                            <td>${commission.number}</td>
                            <td>${commission.eventDateAsString}</td>
                            <td><a href="commission/more/${commission.id}">More</a></td>
                        </tr>
                    <#else>
                        <td>no commissions</td>
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
                        <button type="submit" class="btn btn-primary" formaction="/commission/exact"
                                formmethod="get">Exact search</button>
                    </div>
                    <div class="p-1">
                        <button type="submit" class="btn btn-primary" formaction="/commission/imprecise"
                                formmethod="get">Imprecise search</button>
                    </div>
                </form>
            </div>
            <div class="p-2">
                <h5>Add new commission</h5>
                <form method="post">
                    <div class="mb-3 d-flex flex-row align-items-center">
                        <label class="form-label" for="number_id">Number</label>
                        <div class="p-2 d-flex flex-row">
                            <div class="d-flex flex-row">
                                <input id="number_id" type="text" name="number"
                                       class="form-control ${(numberError??)?string('is-invalid', '')} p-2">
                                <#if numberError??>
                                    <div class="invalid-feedback p-2">
                                        ${numberError}
                                    </div>
                                </#if>
                            </div>
                        </div>
                    </div>
                    <div class="mb-3 d-flex flex-row align-items-center">
                        <label class="form-label" for="eventDate_id">Event date</label>
                        <div class="p-2 d-flex flex-row">
                            <div class="d-flex flex-row">
                                <input id="eventDate_id" type="date" name="eventDate"
                                       class="form-control ${(eventDateError??)?string('is-invalid', '')} p-2">
                                <#if eventDateError??>
                                    <div class="invalid-feedback p-2">
                                        ${eventDateError}
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