<template>

    <div id="practice-departments">

        <div class="row">

            <div class="col-md-3">

                <button class="btn btn-light"
                        type="button"
                        data-toggle="collapse"
                        data-target="#departments-list"
                        aria-expanded="false"
                        aria-controls="departments-list">
                    Show <b><u>{{practice.name}}</u></b> departments
                </button>

            </div>

        </div>

        <div class="collapse" id="departments-list">

            <label v-if="departmentsInfo"> {{departmentsInfo.totalcount}} departments found </label>

            <b-pagination
                    v-model="currentPage"
                    :total-rows="rows"
                    :per-page="perPage"
                    aria-controls="departments-table"></b-pagination>

            <p class="mt-3">Current Page: {{ currentPage }}</p>

            <b-table
                    id="departments-table"
                    :items="departmentsInfo.departments"
                    :per-page="perPage"
                    :current-page="currentPage"
                    small></b-table>

        </div>

    </div>

</template>

<script>
    import {ServicesFactory} from "../../services/ServicesFactory";

    const pmsService = ServicesFactory.get("pms");

    export default {
        name: "Departments",
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
                if(this.departmentsInfo) {
                    return this.departmentsInfo.departments.length;
                } else {
                    return 0;
                }
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
