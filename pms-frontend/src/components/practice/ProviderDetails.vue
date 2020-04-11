<template>
    <div id="providerdetails">
        {{providerid}} + {{practiceid}}
    </div>
</template>

<script>
    export default {
        name: "ProviderDetails",
        props: {
            providerid: String,
            practiceid: String
        }
    }
</script>
