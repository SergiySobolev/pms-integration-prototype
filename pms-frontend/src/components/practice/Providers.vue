<template>

    <div id="practice-providers">

        <br/>

        <div class="row">

            <div class="col-md-7">

                <button class="btn btn-light"
                        type="button"
                        data-toggle="collapse"
                        data-target="#providers-list"
                        aria-expanded="false"
                        aria-controls="providers-list">
                    Show <b><u>{{practice.name}}</u></b>   providers
                </button>

            </div>

        </div>


        <div class="collapse" id="providers-list">
            <br/>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-10">
                    <b-pagination
                            v-model="currentPage"
                            :total-rows="rows"
                            :per-page="perPage"
                            aria-controls="providers-table"></b-pagination>
                </div>

            </div>

            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-9">
                    <b-table
                            id="providers-table"
                            :items="departmentsInfo.departments"
                            :per-page="perPage"
                            :current-page="currentPage"
                            small></b-table>
                </div>
                <div class="col-md-1"></div>
            </div>

        </div>

    </div>

</template>

<script>
    import {ServicesFactory} from "../../services/ServicesFactory";

    const pmsService = ServicesFactory.get("pms");

    export default {
        name: "Providers",
        props: {
            practice: Object
        },
        data() {
            return {
                perPage: 5,
                currentPage: 1,
                departmentsInfo: Object
            }
        },
        created: function () {
            this.fetch();
        },
        computed: {
            rows() {
                return 22;
            }
        },
        methods: {
            async fetch() {
                pmsService.getDepartments(this.practice.practiceid).then(res => {
                    this.departmentsInfo = res.data;
                });
            }
        }
    }
</script>
