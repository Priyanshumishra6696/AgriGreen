package com.example.agrigreen.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agrigreen.AgriGreenViewModel
import com.example.agrigreen.ui.components.BottomNavigationBar
import com.example.agrigreen.ui.theme.DarkGreen
import com.example.agrigreen.ui.theme.ParrotGreen

@Composable
fun GovtSchemesScreen(viewModel: AgriGreenViewModel,navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 63.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row {
                        Text(
                            text = "Government",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkGreen,
                            style = TextStyle(textDecoration = TextDecoration.Underline)
                        )
                    }
                    Row {
                        Text(
                            text = "Schemes",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkGreen,
                            style = TextStyle(textDecoration = TextDecoration.Underline)
                        )
                    }
                    Spacer(
                        modifier = Modifier.height(32.dp)
                    )
                }
            }
            itemsIndexed(
                Schemes
            ){index: Int, item: Scheme ->
                SchemeBox(
                    viewModel = viewModel,
                    SchemeName = item.SchemeName,
                    SchemeInformation = item.SchemeInformation,
                    SchemeNumber = index+1
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        BottomNavigationBar(viewModel,navController)
    }
}

@Composable
fun SchemeBox(viewModel: AgriGreenViewModel,SchemeName : String,SchemeInformation : String,SchemeNumber : Int){
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
            .background(ParrotGreen)
            .clickable {
                expanded = !expanded
            }

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier,
                text = "${SchemeNumber.toString()})"+"  "+SchemeName,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = DarkGreen
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            AnimatedVisibility(
                visible = expanded
            ) {
                Text(
                    text = SchemeInformation,
                    color = Color.Black
                )
            }
        }



    }
}

data class Scheme(val SchemeName: String,val SchemeInformation: String)

val Schemes = listOf(
    Scheme(
        SchemeName = "Pradhan Mantri Kisan Samman Nidhi (PM-KISAN)",
        SchemeInformation = "PM-KISAN is a central sector scheme launched on 24th February 2019 to supplement financial needs of land holding farmers, subject to exclusions. Under the scheme, financial benefit of Rs. 6000/- per year is transferred in three equal four-monthly installments into the bank accounts of farmers’ families across the country, through Direct Benefit Transfer (DBT) mode. Till now, Rs.\n" +
                "\n" +
                "2.81 lakh crores have been transferred through Direct Benefit Transfer (DBT) to more than 11 crores beneficiaries (Farmers) through various instalments."
    ),
    Scheme(
        SchemeName = "Pradhan Mantri Kisan MaanDhan Yojana (PM-KMY)",
        SchemeInformation = "Pradhan Mantri Kisan Maandhan Yojna (PMKMY) is a central sector scheme launched on 12th September 2019 to provide security to the most vulnerable farmer families. PM-KMY is contributory scheme, small and marginal farmers (SMFs), subject to exclusion criteria, can opt to become member of the scheme by paying monthly subscription to the Pension Fund. Similar, amount will be contributed by the Central Government.\n" +
                "\n" +
                "The applicants between the age group of 18 to 40 years will have to contribute between Rs. 55 to Rs. 200 per month till they attain the age of 60. PMKMY is taking care of the farmers during their old age and provides Rs. 3,000 monthly pension to the enrolled farmers once they attain 60 years of age, subject to exclusion criteria.\n" +
                "\n" +
                "Life Insurance Corporation (LIC) is pension fund manager and registration of beneficiaries is done through CSC and State Govts.\n" +
                "\n" +
                "So far 23.38 lakh farmers have enrolled under the scheme."
    ),
    Scheme(
        SchemeName = "Pradhan Mantri Fasal Bima Yojana (PMFBY)",
        SchemeInformation = "PMFBY was launched in 2016 in order to provide a simple and affordable crop insurance product to ensure comprehensive risk cover for crops to farmers against all non-preventable natural risks from pre-sowing to post-harvest and to provide adequate claim amount. The scheme is demand driven and available for all farmers A total of 5549.40 lakh farmer applications were insured under the\n" +
                "\n" +
                "scheme since 2016-17 and Rs 150589.10 crore has been paid as claim."
    ),
    Scheme(
        SchemeName = "Modified Interest Subvention Scheme (MISS)",
        SchemeInformation = "The Interest Subvention Scheme (ISS) provides concessional short term agri-loans to the farmers practicing crop husbandry and other allied activities like animal husbandry, dairying and fisheries. ISS is available to farmers availing short term crop loans up to\n" +
                "\n" +
                "Rs.3.00 lakh at an interest rate of 7% per annum for one year. Additional 3% subvention is also given to the farmers for prompt and\n" +
                "\n" +
                "timely repayment of loans thus reducing the effective rate of interest to 4% per annum. The benefit of ISS is also available for post-harvest loans against Negotiable Warehouse Receipts (NWRs) on crop loans for a further period of six months post-harvest to small and marginal farmers having Kisan Credit Cards (KCCs), on occurrence of natural calamities and severe natural calamities. As on 05-01-2024, 465.42\n" +
                "\n" +
                "lakh new KCC applications have been sanctioned with a sanctioned credit limit of Rs. 5,69,974 crore as part of the drive."
    ),
    Scheme(
        SchemeName = "Agriculture Infrastructure Fund (AIF)",
        SchemeInformation = "In order to address the existing infrastructure gaps and mobilize investment in agriculture infrastructure, Agri Infra Fund was launched under Aatmanirbhar Bharat Package. AIF was introduced with a vision to transform the agriculture infrastructure landscape of the country. The Agriculture Infrastructure Fund is a medium - long term debt financing facility for investment in viable projects for post- harvest management infrastructure and community farming assets through interest subvention and credit guarantee support. The Fund of Rs. 1 lakh crore under the scheme will be disbursed from FY 2020-21 to FY2025-26 and the support under the scheme will be provided for the duration of FY2020-21 to FY2032-33.\n" +
                "\n" +
                "Under the scheme, Rs. 1 Lakh Crore will be provided by banks and financial institutions as loans with interest subvention of 3% per annum and credit guarantee coverage under CGTMSE for loans up to Rs. 2 Crores. Further, each entity is eligible to get the benefit of the scheme for up to 25 projects located in different LGD codes.\n" +
                "\n" +
                "Eligible beneficiaries include Farmers, Agri-entrepreneurs, Start-ups, Primary Agricultural Credit Societies (PACS), Marketing Cooperative Societies, Farmer Producers Organizations(FPOs), Self Help Group (SHG), Joint Liability Groups (JLG), Multipurpose Cooperative Societies, Central/State agency or Local Body sponsored Public Private Partnership Projects, State Agencies, Agricultural Produce Market Committees (Mandis), National & State Federations of Cooperatives, Federations of FPOs (Farmer Produce Organizations) and Federations of Self Help Groups (SHGs).\n" +
                "\n" +
                "As on 31-12-2023, Rs.33.209 Crores have been sanctioned for 44,912 projects under AIF, out of this total sanctioned amount, Rs 25,504 Crores is covered under scheme benefits. These sanctioned projects have mobilized an investment of Rs 56.471 Crores in agriculture sector."
    ),
    Scheme(
        SchemeName = "Formation & Promotion of new 10,000 FPOs",
        SchemeInformation = "The Government of India launched the Central Sector Scheme (CSS) for “Formation and Promotion of 10,000 Farmer Producer Organizations (FPOs)” in the year 2020. The scheme has a total budgetary outlay of Rs.6865 crores. Formation & promotion of FPOs are to be done through Implementing Agencies (IAs), which further engage Cluster Based Business Organizations (CBBOs) to form & provide professional handholding support to FPOs for a\n" +
                "\n" +
                "period of 5 years." + "\n"+
                "FPOs get a financial assistance upto Rs 18.00 lakh per FPO for a period of 03 years. In addition to this, provision has been made for matching equity grant upto Rs. 2,000 per farmer member of FPO with a limit of Rs. 15.00 lakh per FPO and a credit guarantee facility upto Rs. 2 crore of project loan per FPO from eligible lending institution to ensure institutional credit accessibility to FPOs. Suitable provisions have been made for training and skill development of FPOs.\n" +
                "\n" +
                "Further, FPOs are onboarded on National Agriculture Market (e-NAM) platform which facilitate online trading of their agricultural commodities through transparent price discovery method to enable FPOs to realize better remunerative prices for their produce.\n" +
                "\n" +
                "As on 31.12.2023, total 7,774 FPOs were registered under the scheme in the country."
    ),
    Scheme(
        SchemeName = "National beekeeping  and Honey Mission (NBHM)",
        SchemeInformation = "Keeping in view the importance of beekeeping, a new Central Sector Scheme entitled National Beekeeping & Honey Mission (NBHM) was launched in 2020 under Atma Nirbhar Bharat Abhiyan for its implementation in the field for overall promotion and development of scientific beekeeping & to achieve the goal of “Sweet Revolution”. Some of the achievements include;\n" +
                "\n" +
                "Honeybees/ beekeeping have been approved as 5th Input for Agriculture.\n" +
                "4 World Class State of the Art Honey Testing Labs and 35 Mini Honey Testing Labs have been sanctioned under National Beekeeping & Honey Mission (NBHM) for testing of honey.\n" +
                "Madhukranti portal has been launched for online registration of Beekeepers/ Honey Societies/ Firms/ Companies.\n" +
                "Till date 23 lakhs bee colonies registered on Portal.\n" +
                "100 Honey FPOs targeted under 10,000 FPOs scheme in the country. 88 FPOs have been registered by NAFED, NDDB & TRIFED.\n" +
                "25 States/UTs have been covered under NBHM under MM-I, II & III.\n" +
                "160 Projects sanctioned under MM- I, II & III of Rs. 202.00 crores."
    ),
    Scheme(
        SchemeName = "Market Intervention Scheme and Price support Scheme (MIS-PSS)",
        SchemeInformation = "Ministry of Agriculture & Farmers Welfare implements the Price Support Scheme (PSS) for procurement of pulses, oilseeds and copra. Market Intervention Scheme (MIS) for procurement of agricultural and horticultural commodities which are perishable in nature and are not covered under the Price Support Scheme (PSS). The objective of intervention is to protect the growers of these commodities from making distress sale in the event of a bumper crop during the peak arrival period when the prices tend to fall below\n" +
                "\n" +
                "economic levels and cost of production."
    ),
    Scheme(
        SchemeName = "Namo Drone Didi",
        SchemeInformation = "The Government has recently approved a Central Sector Scheme for\n" +
                "\n" +
                "providing drones to the Women Self Help Group (SHGs) for the\n" +
                "\n" +
                "period from 2024-25 to 2025-26 with an outlay of Rs. 1261 Crores. The scheme aims to provide drones to 15000 selected Women Self Help Group (SHGs) for providing rental services to farmers for agriculture purpose (application of fertilizers and pesticides). Under this Scheme, Central Financial Assistance @ 80% of the cost of drone and accessories/ancillary charges upto a maximum of Rs. 8.0 Lakhs will be provided to the women SHGs for purchase of drones. The Cluster Level Federations (CLFs) of SHGs may raise the balance amount (total cost of procurement minus subsidy) as loan under National Agriculture Infra Financing Facility (AIF). Interest subvention @ 3% on the AIF loan will be provided to the CLFs. The scheme will also provide sustainable business and livelihood support\n" +
                "\n" +
                "to SHGs and they would be able to earn additional income of at least of Rs. 1.0 lakh per annum."
    ),
    Scheme(
        SchemeName = "Rastriya Krishi Vikas Yojana-Detailed Project Report based schemes (RKVY- DPR)",
        SchemeInformation = "The scheme focuses on creation of pre & post-harvest infrastructure in agriculture and allied sectors that help in supply of quality inputs, market facilities, etc to farmers. It provides flexibility and autonomy to states to implement projects as per the local farmers’ needs and priorities from a bouquet of activities in agriculture and allied sectors. The scheme aims to fill the resources gap of agriculture and allied sectors by providing financial support to states for undertaking various activities to increase in overall growth of agriculture and allied sectors and farmers’ income.\n" +
                "\n" +
                "Under RKVY Agri-startup Programme, since 2019-20, 1524 Start-ups have been selected and Rs. Rs. 106.25 crore released as grants-in-aid for funding the Start-ups."
    ),
    Scheme(
        SchemeName = "Soil Health Card (SHC)",
        SchemeInformation = "Soil health card provides information to farmers on nutrient status of their soil along with recommendation on appropriate dosage of nutrients to be applied for improving soil health and its fertility. The indicators are typically based on farmers' practical experience and knowledge of local natural resources. The card lists soil health indicators that can be assessed without the aid of technical or laboratory equipment. The Scheme rolls out a decentralized system of soil testing which will help in developing a nationwide soil fertility map on a GIS platform that can easily be integrated with the real time decision support systems being developed. In order to develop the soil fertility map, Government of India has decided to conduct 5 Crore Soil Samples across the country during year 2023-\n" +
                "\n" +
                "24 to 2025-26."
    ),
    Scheme(
        SchemeName = "Rainfed Area Development (RAD)",
        SchemeInformation = "RAD is being implemented since 2014-15. RAD adopts an area based approach in cluster mode for promoting Integrated Farming System (IFS) which focuses on multi-cropping, rotational\n" +
                "\n" +
                "cropping, inter-cropping, mixed cropping practices with allied activities like horticulture, livestock, fishery, apiculture etc. to\n" +
                "\n" +
                "enable farmers not only in maximizing the farm returns for sustaining livelihood, but also to mitigate the impacts of drought, flood or other extremes weather events. An amount of Rs. 1673.58\n" +
                "\n" +
                "crores has been released and an area of 7.13 lakh hectare has been covered under RAD programme from the year 2014-15 to till date."
    ),
    Scheme(
        SchemeName = "Per Drop More Crop (PDMC)",
        SchemeInformation = "In order to increase water use efficiency at the farm level through Micro Irrigation technologies i.e. drip and sprinkler irrigation systems, Per Drop More Crop (PDMC) scheme was launched during 2015-16. The Micro Irrigation helps in water saving as well as reduced fertilizer usage through fertigation, labour expenses, other input costs and overall income enhancement of farmers.\n" +
                "\n" +
                "It also supports micro level water harvesting, storage, management etc. activities as Other Interventions (OI) to supplement source creation for Micro Irrigation. OI activities allowed on need basis up to 40% of the total allocation for North East States, Himalayan States, Jammu & Kashmir, Ladakh and up to 20% for other States.\n" +
                "\n" +
                "An area of 78 lakh hectare has been covered under Micro irrigation through the PDMC scheme from 2015-16 to 2022-23."
    ),
    Scheme(
        SchemeName = "Micro  Irrigation Fund (MIF)",
        SchemeInformation = "A Micro Irrigation Fund (MIF) of initial corpus Rs 5000 crore has been created with NABARD with major objective to facilitate the States in mobilizing the resources for expanding coverage of Micro Irrigation. Under the funding arrangement, NABARD lends to the States/UTs at 3% lower interest rate than the corresponding cost of fund mobilized by NABARD from the market. The interest subvention on the loan under MIF is borne by Centre under PDMC. Projects with loans under MIF worth Rs 4710.96 crore have been approved so far. Loans amounting Rs.2812.24 crore has been disbursed to States of Andhra Pradesh, Tamil Nadu, Gujarat, Punjab, Haryana and Rajasthan. The Ministry provides interest subvention on the loan availed by the States which is met from PDMC Scheme. As per the Budget 2021-22, the corpus of the fund is to be doubled to Rs.10000 crores. MIF is now merged with\n" +
                "\n" +
                "PDMC."
    ),
    Scheme(
        SchemeName = "Paramparagat Krishi Vikas Yojana (PKVY)",
        SchemeInformation = "Paramparagat Krishi Vikas Yojana (PKVY) aims to increase soil fertility and thereby helps in production of healthy food through organic practices without the use of agro-chemicals. The scheme is implemented in a cluster mode with unit cluster size of 20 hectares. A group shall comprise minimum 20 farmers (may be more if individual holdings are less). Farmers in a group can avail benefit of maximum of 2 ha as per provision of PKVY. 25 such clusters are converted into one large cluster of about 500 ha area to facilitate marketing of organic produce. The scheme provides for an assistance of Rs. 31,500 per ha to states, out of which i.e., Rs. 15,000 is given\n" +
                "\n" +
                "as incentives to a farmer directly through DBT."
    ),
    Scheme(
        SchemeName = "Sub-Mission  on Agriculture Mechanization (SMAM)",
        SchemeInformation = "Sub Mission on Agricultural Mechanization (SMAM) is being implemented w.e.f April, 2014 which aims at catalyzing an accelerated but inclusive growth of agricultural mechanization in India with the objectives of Increasing the reach of farm mechanization to small and marginal farmers and to the regions where availability of farm power is low, promoting ‘Custom Hiring Centres’ to offset the adverse economies of scale arising due to small landholding and high cost of individual ownership, creating hubs for hi-tech& high value farm equipments, creating awareness among stakeholders through demonstration and capacity building activities and Ensuring performance testing and certification at designated testing centers located all over the country. Till date Rs. 6748.78 Crore have been released to State Governments, distributed more than 15,75,719 agricultural machinery & equipment’s including Tractors, Power Tillers, Self-Propelled Machineries and Plant Protection Equipment and established 23472 nos of Custom Hiring Centres, 504 nos of Hi-Tech Hubs and 20597 nos. of Farm Machinery Banks.\n" +
                "\n" +
                "Promotion of Drone Technology under SMAM\n" +
                "\n" +
                "Looking into the unique advantages of Drone technologies in agriculture, a Standard Crop Specific Operating Procedures (SOPs) released the for use of drones in pesticide and nutrient application in public domain on 20.04.2023, which provides concise instructions for effective and safe operations of drones.\n" +
                "\n" +
                "From within the funds of SMAM, so far an amount of Rs.\n" +
                "\n" +
                "138.82 crores have been released towards Kisan drone promotion, which include purchase of 317 Drones for their demonstration in 79070 hectares of land and supply of 461 drones to the farmers on subsidy and also supply of 1595 drones to the CHCs for providing drone services to the farmers on rental basis."
    ),
    Scheme(
        SchemeName = "Crop Residue Management",
        SchemeInformation = "Crop Residue Management was implemented from 2018-19 in Punjab, Haryana, Uttar Pradesh and NCT of Delhi. Its objectives include protecting environment from air pollution and preventing loss of nutrients and soil micro-organisms caused by burning of crop residue through promoting in-situ management of crop residue. In this regard, it proposes to set up Farm Machinery Banks for custom hiring of in-situ crop residue management machinery. It also aims to creating awareness among stakeholders through demonstration, capacity building activities and differentiated information, education and communication strategies for effective utilization and management of crop residue. Rs. 3333.17 crore has been released under the scheme since inception and distributed more than 2,95,845 CRM machinery. CRM is now merged with\n" +
                "\n" +
                "SMAM."
    ),
    Scheme(
        SchemeName = "Agro-forestry",
        SchemeInformation = "Agro-forestry was conceived on the recommendation of the National Agro-forestry Policy 2014 to promote plantation on farmlands. The\n" +
                "\n" +
                "restructured agro-forestry under RKVY is aimed to provide Quality\n" +
                "\n" +
                "Planting Materials (QPM) and the certification in order to promote\n" +
                "\n" +
                "planting of trees on farm land for improving the livelihood of farmers."
    ),
    Scheme(
        SchemeName = "National Food Security Mission (NFSM)",
        SchemeInformation = "The Mission aims at increasing production of rice, wheat, pulses, coarse cereals (Maize and Barley) and Nutri-Cereals through area expansion and productivity enhancement in a sustainable manner in the identified districts of 28 States and 2 UTs (i.e., J&K and Ladakh). Other objectives include restoring Soil fertility and productivity at the individual farm level, enhancing farm level economy to restore confidence amongst the farmers and post harvest value addition at farm gate.\n" +
                "\n" +
                "Since the declaration of the International Year of Millets (IYM) 2023 by the UNGA in 2021, Government has taken a proactive multi stakeholder engagement approach to achieve the aim of IYM 2023 and taking Indian millets globally. 25 seed-hubs have been established to ensure availability of quality seed of latest improved varieties of Nutri cereals in the country. Millet missions have been launched across 13 states including Odisha, Tamil Nadu, Chhattisgarh, Assam, Karnataka, Madhya Pradesh, Maharashtra, Uttarakhand, Uttar Pradesh, Bihar, Himachal Pradesh, Gujarat and Rajasthan. More than 500 start-ups and 350 FPOs have been\n" +
                "\n" +
                "established and are operational in the millet ecosystem as of now."
    ),
    Scheme(
        SchemeName = "Sub-Mission on Seed and Planting Material (SMSP)",
        SchemeInformation = "SMSP covers the entire gamut of seed production chain, from production of nucleus seed to supply of certified seeds to the farmers, to provide support for creation of infrastructure conducive for development of the seed sector, support to the public seed producing organisations for improving their capacity and quality of seed production, create dedicated seed bank to meet unforeseen circumstances of natural calamities, etc. For effective monitoring, efficiency and transparency covering Seed chain from Nucleus- Breeder-Foundation-Certified Seed, first phase of Seed Authentication, Traceability & Holistic Inventory (SATHI) portal\n" +
                "\n" +
                "was launched on 19th April, 2023. SMSP is now merged with NFSM."
    ),
    Scheme(
        SchemeName = "National Mission on Edible Oils (NMEO)-Oil Palm",
        SchemeInformation = "A new Centrally Sponsored Scheme namely, National Mission on Edible Oil (NMEO)-Oil Palm (NMEO-OP) has been launched by Government of India in 2021 in order to promote oil palm cultivation for making the country Aatamnirbhar in edible oils with special focus on North-Eastern States and A&N Islands. The Mission will bring additional area of 6.5 lakh ha under Oil Palm plantation with\n" +
                "\n" +
                "3.28 lakh ha in north-eastern states and 3.22 in rest of India in next 5 years from 2021-22 to 2025-26."
    ),
    Scheme(
        SchemeName = "Mission for Integrated Development of Horticulture (MIDH)",
        SchemeInformation = "Mission for Integrated Development of Horticulture (MIDH), a Centrally Sponsored Scheme was launched during 2014-15 for holistic growth of the horticulture sector covering fruits, vegetables, root and tuber crops, mushrooms, spices, flowers, aromatic plants, coconut, cashew, cocoa and Bamboo. Major components include\n" +
                "\n" +
                "plantation infrastructure development, establishment of new orchards and gardens for fruits, vegetables, spices and flowers, rejuvenation of unproductive, old, and senile orchards, protected cultivation, promotion of organic farming, pollination support through bee keeping, horticulture mechanization, post-harvest management (phm) and marketing infrastructure etc.\n" +
                "\n" +
                "Under MIDH since 2014-15 to 2023-24 (as on 31.10.2023) an additional area of 12.95 lakh ha. of identified horticulture crops has been covered, 872 nurseries established for production of quality planting material, 1.41 lakh ha. of old and senile orchards has been rejuvenated, 52069 ha. been covered under organic practices and\n" +
                "\n" +
                "3.07 lakh ha. has been covered under Protected Cultivation."
    ),
    Scheme(
        SchemeName = "National Bamboo Mission (NBM)",
        SchemeInformation = "The Scheme is implemented in 23 States and 1 UT (J&K) through the State Bamboo Missions (SBM)/ State Bamboo Development Agency (SBDA).NBM mainly focus on the development of complete value chain of the bamboo sector. It is envisaged to link growers with consumers with a cluster approach mode.\n" +
                "\n" +
                "Under NBM, 367 Bamboo Nurseries established, 212 bamboo Nurseries Accredited by the State Level Accreditation Committees, 46000 ha bamboo plantations established in non-forest Government & private lands, 81 units for bamboo primary processing established, 416 units established for value addition and product development, and capacity building for 15000 persons including farmers, artisans\n" +
                "\n" +
                "and entrepreneurs. NBM is now merged with MIDH."
    ),
    Scheme(
        SchemeName = "Integrated Scheme for Agriculture Marketing (ISAM)",
        SchemeInformation = "ISAM supports state governments in governing the agricultural produce marketing through creation and improvement of market structures, capacity building and generating access to market information. During 2017-18, National Agriculture Market Scheme popularly known as e-NAM scheme has also been made part of the same. National Agriculture Market (e-NAM) is a pan-India electronic trading portal which networks the existing APMC mandis to create a unified national market for agricultural commodities. 1389 mandis of 23 States and 04 UTs have been integrated to e- NAM platform and more than 1.76 Crore Farmers & 2.5 Lakh traders\n" +
                "\n" +
                "have been registered on e-NAM portal."
    ),
    Scheme(
        SchemeName = "Mission Organic Value Chain Development for North Eastern Region",
        SchemeInformation = "The MOVCDNER aims at development of commodity specific, concentrated, certified organic production clusters in value chain mode to link growers with consumers and to support the development of entire value chain starting from inputs, seeds, certification, to the creation of facilities for collection, aggregation, processing, marketing and brand building initiative in Northeast Region (Arunachal Pradesh, Assam, Manipur, Meghalaya, Mizoram, Nagaland, Sikkim, and Tripura). Since 2015-16 (as on 06.12.2023), Rs 1035.17 crore has been released, 379 FPO/FPCs created covering\n" +
                "\n" +
                "189039 farmers and 172966 ha area."
    ),
    Scheme(
        SchemeName = "Sub-Mission on Agriculture Extension (SMAE)",
        SchemeInformation = "The scheme aims at making the extension system farmer driven and farmer accountable by disseminating technology to farmers through new institutional arrangements viz. Agricultural Technology Management Agency (ATMA) at district level to operationalize extension reforms in a participatory mode. Digital initiatives taken up in agricultural extension include;\n" +
                "\n" +
                "VISTAAR - Virtually integrated Systems To Access Agricultural Resourcs being developed as a DPI for Agriculture Extension\n" +
                "Apurva AI- Capturing farmer innovations- Acts as a peer to peer learning Platform and provide content for advisory retrieval through VISTAAR Bot and also for impact Assessment of schemes (AIF completed)\n" +
                "Wadhwani- Krishi 24X7 for Realtime News monitoring, Tamil language and image-based cotton pest identification to be plugged in with FLEW/farmer profile mapping\n" +
                "Kisan Call Centre - Integration with VISTAAR and other IT applications and with Kisan Sarathi (ICAR) for direct contact with Agri experts\n" +
                "RAWE- Integration of Agri students for behavioral interaction through VISTAAR Bot and Feedback system\n" +
                "IMD- Weather forecast integrated through DAMU along with advisory delivery through VISTAAR\n" +
                "NRLM- Decentralised Extension Mechanism ( Krishi Sakhi, Pashu Sakhi , Matsya Sakhi etc) - Capacity building on Digital Extension -VISTAAR"
    ),
    Scheme(
        SchemeName = "Digital Agriculture",
        SchemeInformation = "The scheme aims to improve the existing National e- Governance Plan in Agriculture (NeGPA) by developing a digital public infrastructure for agriculture that will be built as an open source, open standard and interoperable public good to enable inclusive, farmer-centric solutions through relevant information services for crop planning and health, improved access to farm inputs, credit and insurance, help for crop estimation, market intelligence, and support for the growth of Agri Techs industry and start-ups.\n" +
                "\n" +
                "AgriStack architecture has the following foundational layers: -\n" +
                "\n" +
                "Core registries\n" +
                "Base databases\n" +
                "Farmers Database: Farmers ID linked with land records\n" +
                "Geo-referencing of plots\n" +
                "Crop Survey, Crop planning and\n" +
                "Soil Mapping, Soil Fertility\n" +
                "Unified Farmers Service Interface for state, Pvt. Players\n" +
                "Data Exchange"
    )


)
