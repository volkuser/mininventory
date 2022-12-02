<#import "parts/common.ftl" as c>

<@c.page>
    <h3>Commission list for this employee</h3>
    <div class="d-flex flex-row mb-3">
        <div class="p-2">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Number</th>
                    </tr>
                </thead>
                <tbody>
                    <#list personalCommissions as commission>
                        <tr>
                            <td>${commission.id}</td>
                            <td>${commission.number}</td>
                            <form>
                                <td><button class="btn btn-danger" formmethod="post"
                                            formaction="/commission/more/${actualId}/list/${commission.id}/delete">
                                        Remove
                                    </button></td>
                            </form>
                        </tr>
                    <#else>
                        <td>no commissions in relationship with this employee</td>
                    </#list>
                </tbody>
            </table>
        </div>
        <div class="d-flex flex-column mb-3">
            <div class="p-2">
                <h5>Create new relationship</h5>
                <form>
                    <div class="mb-3">
                        <label class="form-label">Commission</label>
                        <label>
                            <select name="commission" class="form-control">
                                <#list commissions as commission>
                                    <option value="${commission.id}">${commission.number}</option>
                                </#list>
                            </select>
                        </label>
                    </div>
                    <a type="button" class="btn btn-primary" href="/employee/more/${actualId}">Back</a>
                    <button type="submit" formmethod="post" class="btn btn-primary">Create</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>