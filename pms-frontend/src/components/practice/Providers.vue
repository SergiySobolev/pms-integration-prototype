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
                    Show <b><u>{{practice.name}}</u></b> providers
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
                            :items="providersInfo.providers"
                            :fields="fields"
                            :per-page="perPage"
                            :current-page="currentPage"
                            :head-variant="headVariant"
                            :striped="true"
                            :bordered="true"
                            :hover="true"
                    >

                        <template v-slot:cell(actions)="data">

                            <router-link
                                    :to="{ name: 'providerdetails',
                                           params: {
                                                    practiceid: practice.practiceid,
                                                    providerid: data.item.providerid
                                    }}">

                                <input type="button" value="Book Appointment" class="btn btn-info"/>

                            </router-link>

                        </template>

                    </b-table>

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
                dataLoaded: false,
                perPage: 5,
                currentPage: 1,
                providersInfo: {
                    Type: Object,
                    default: null
                },
                headVariant: "dark",
                fields: [
                    {key: 'firstname', label: 'First Name',  sortable: true},
                    {key: 'lastname', label: 'Last Name',  sortable: true},
                    {key: 'ansinamecode', label: 'Ansi Name Code'},
                    {key: 'providertypeid', label: 'Type'},
                    {key: 'providerid', label: 'ID'},
                    {key: 'schedulingname', label: 'Scheduling Name'},
                    {key: 'actions', label: 'Actions'}
                ]
            }
        },
        created: function () {
            this.fetch();
        },
        computed: {
            rows() {
                return this.dataLoaded
                    ? this.providersInfo.providers.length
                    : 0
            }
        },
        methods: {
            async fetch() {
                pmsService.getProviders(this.practice.practiceid).then(res => {
                    this.providersInfo = res.data;
                    this.dataLoaded = true
                });
            }
        }
    }
</script>
