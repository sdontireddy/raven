my $m = 720;	#Number of DO
my $o = 2;	#Number of DO Detail


our $template1 = q{<?xml version="1.0" encoding="utf-8"?>
<tXML>
	<Message>	
		<DistributionOrder>
			<DistributionOrderId>AIML1125202SET1#1#</DistributionOrderId>
			<OrderType>EC</OrderType>
			<OrderedDttm>11/01/2020 11:44</OrderedDttm>
			<DoTransportationStatus>Unplanned</DoTransportationStatus>
			<DoFulfillmentStatus>Released</DoFulfillmentStatus>			
			<SynchGroupId/>
			<SynchronizeLock/>
			<UpdateActionType/>
			<ActualShippedDttm>&3/7/16&T11:44:00</ActualShippedDttm>
			<IsCancelled>false</IsCancelled>
			<BusinessUnit>1</BusinessUnit>
			<MerchandizingDept/>
			<MonetaryValue/>
			<MonetaryValueCurrencyCode/>
			<TransportationResponsibilityCode>SHP</TransportationResponsibilityCode>
			<BillingMethodCode/>
			<BusinessPartnerId/>
			<MustReleaseByDttm/>
			<IsOriginalOrder>true</IsOriginalOrder>
			<HasSplit>false</HasSplit>	
			<ParentOrderId/>
			<ProductClassCode/>
			<IsPerishable>false</IsPerishable>
			<ProtectionLevelCode/>
			<IsHazmat>false</IsHazmat>
			<DangerousGoodsCode/>
			<ProductionScheduleRefNbr/>
			<Priority>1</Priority>
			<OriginalBudgetedCost/>
			<BudgetedCost/>
			<BudgetedCostCurrencyCode>USD</BudgetedCostCurrencyCode>
			<ActualCost/>
			<TotalActualCost/>
			<TotalActualCostCurrencyCode>USD</TotalActualCostCurrencyCode>

			<ReferenceNumberField1>10</ReferenceNumberField1>
			<ReferenceNumberField2/>
			<ReferenceNumberField3/>
			<ReferenceNumberField4/>
			<ReferenceNumberField5/>
			<NmfcFreightClass/>
			<OriginalEstimatedNbrOfPalletsBridged>0</OriginalEstimatedNbrOfPalletsBridged>
			<OriginalEstimatedNbrOfLpnsBridged>0</OriginalEstimatedNbrOfLpnsBridged>
			<FinalEstimatedNbrOfLpns>0</FinalEstimatedNbrOfLpns>
			<PlanningOriginFacilityId>70</PlanningOriginFacilityId>
			<PlanningDestinationFacilityId/>
			<MoveType/>
			<MovementOption>Unrestricted</MovementOption>
			<TransportationWaveId/>
			<TransportationWaveOptionId/>
			<ResidentialDeliveryRequired>NEI</ResidentialDeliveryRequired>
			<DropoffOrPickup/>
			<Packaging/>
			<IncotermName/>
			<IncotermLocationAvailabilityDttm/>
			<DistributionShipVia/>
			<BlockAutoCreateFlag>false</BlockAutoCreateFlag>
			<BlockAutoConsolidateFlag>false</BlockAutoConsolidateFlag>
			<IsPartiallyPlanned>false</IsPartiallyPlanned>
			<IsBackOrdered>false</IsBackOrdered>
			<AdvertizingDate/>
			<CustomerCode/>
			<CustomerName>Customer1</CustomerName>
			<DcCenterNbr/>
			<FederatedStoreNbr/>
			
			<OriginFacilityAliasId>70</OriginFacilityAliasId>
			<OriginDockId/>
			<OriginFacilityName>Bloomington</OriginFacilityName>
			<OriginAddressLine1>6203 West 111th Street</OriginAddressLine1>
			<OriginAddressLine2/>
			<OriginAddressLine3/>
			<OriginCity>Bloomington</OriginCity>
			<OriginCounty/>
			<OriginCountry>US</OriginCountry>
			<OriginStateOrProvince>MN</OriginStateOrProvince>
			<OriginPostalCode>55438</OriginPostalCode>
			<HazmatOfferorName/>
			<PickupStartDttm/>
			<PickupEndDttm/>
			<OriginShipThruFacilityAliasId/>
			<ScheduledDayOfWeek/>
			
			<DestinationFacilityAliasId>22</DestinationFacilityAliasId>
			<DestinationDockId/>
			<DestinationFacilityName>DENVER</DestinationFacilityName>
			<DestinationAddressLine1>1234 TEST</DestinationAddressLine1>
			<DestinationAddressLine2/>
			<DestinationAddressLine3/>
			<DestinationCity>DENVER</DestinationCity>
			<DestinationCounty/>
			<DestinationCountry>US</DestinationCountry>
			<DestinationStateOrProvince>CO</DestinationStateOrProvince>
			<DestinationPostalCode>80205</DestinationPostalCode>
			<POBox>false</POBox>
			<DeliveryStartDttm/>
			<DeliveryEndDttm/>

			<PackAndHoldFlag>0</PackAndHoldFlag>
			<PreStickerCode/>
			<MajorOrderGroupAttribute/>
			<PrintedDttm>3/24/16 20:24</PrintedDttm>
			<LpnCubingIndicator>51</LpnCubingIndicator>
			<PalletCubingIndicator>0</PalletCubingIndicator>
			<PrepackCubingIndicator>0</PrepackCubingIndicator>
			<PalletLpnOption>C</PalletLpnOption>
			<ShipGroupId/>
			<ShipGroupSequence/>
			<RefShipmentNbr/>
			<RefShipmentStopSeqNbr/>
			<BillOfLadingNbr/>
			<BolBreakAttribute/>
			<DesignatedZoneSkip/>
			<CompartmentNbr/>
			<Importer/>
			<CustomsBrokerAccountNbr/>
			<HasDocumentsOnly>false</HasDocumentsOnly>
			<RelatedPartiesFlag>false</RelatedPartiesFlag>
			<InternationalGoodsDescription/>

			<OrderGenerationDate>3/24/16 20:21</OrderGenerationDate>
			<SchedDlvryEndDate>3/20/16 11:49</SchedDlvryEndDate>
			<ZoneSkip/>
			<IsCustomerPickup/>
			<IsDirectAllowed/>
			<ManifestNbr/>
			<OrderConsolidationProfile/>
			<DistributionOrderType>Customer Order</DistributionOrderType>
			<RoutingAttribute/>
			<SchedPickupDttm/>
			<NonMachinable>false</NonMachinable>
			<CodReturnCompanyName/>
			<IncotermFacilityId/>
			<DsgShipVia></DsgShipVia>
			<DsgStaticRouteId/>
			<DeliveryChannel/>
			<PartlShipConfFlag>true</PartlShipConfFlag>
			<AllowPreBilling>false</AllowPreBilling>
			<LaneName/>
			<DeclaredValue/>
			<DeclaredValueCurrencyCode/>
			<TaxId/>
			<OrderReceived>false</OrderReceived>
			<CommodityCode/>
			<NumSplits>0</NumSplits>
			<LoadingSequence/>	
			<DestinationAction/>
			<DeliveryOptions/>
			<TransportationPlanningOwner/>
			<TransportationPlanningDirection/>
			<PreBillStatus>0</PreBillStatus>
			<LpnLabelType/>
			<NbrOfShippingLabelsToPrint/>
			<PalletContentLabelType/>
			<ContentLabelType/>
			<NbrOfContentLabelsToPrint/>
			<PackingSlipType/>
			<NbrOfPackingSlipsToPrint/>
			<BolType/>
			<ManifestType/>
			<PrintCertificateOfOrigin>false</PrintCertificateOfOrigin>
			<PrintCommercialInvoice>false</PrintCommercialInvoice>
			<PrintExportDeclaration>false</PrintExportDeclaration>
			<PrintCanadianCustomsInvoice>false</PrintCanadianCustomsInvoice>
			<PrintDockReceipt>false</PrintDockReceipt>
			<PrintNaftaCertificateOfOrigin>false</PrintNaftaCertificateOfOrigin>
			<PrintOceanBillOfLading>false</PrintOceanBillOfLading>
			<PrintExportPackingList>false</PrintExportPackingList>
			<PrintLetterOfInstructions>false</PrintLetterOfInstructions>	
			<GuaranteedDelivery>false</GuaranteedDelivery>
			<ApplyLPNType>false</ApplyLPNType>
			<POTypeAttribute/>
			<CommoditySize>
			</CommoditySize>	
			<AccessorialOptionGroupList>
			</AccessorialOptionGroupList>};

	   
	  
our $template3 = q{
	
	 <LineItem>
				<DoLineNbr>%1%</DoLineNbr>
				<ItemName>#10010#</ItemName>
				<Description></Description>
				<UpdateActionType/>
				<PackageType/>
				<DoLineStatus>Released</DoLineStatus>
				<IsCancelled>false</IsCancelled>
				<Length/>
				<LengthUOM>In</LengthUOM>
				<Width/>
				<WidthUOM>In</WidthUOM>
				<Height/>
				<HeightUOM>In</HeightUOM>
				<Diameter/>
				<DiameterUOM>In</DiameterUOM>
				<CustomerItemNbr/>
				<ChuteAssignType/>
				<AllocationSourceType>OnHand</AllocationSourceType>
				<TotalMonetaryValue/>
				<MonetaryValueCurrencyCode/>
				<OriginalBudgetedCost/>
				<BudgetedCost/>
				<BudgetedCostCurrencyCode>USD</BudgetedCostCurrencyCode>
				<ActualCost/>
				<ActualCostCurrencyCode>USD</ActualCostCurrencyCode>
				<NmfcFreightClass>FAK</NmfcFreightClass>
				<CommodityCode/>
				<IsHazmat>false</IsHazmat>
				<LineType>Item</LineType>
				<RetailPrice>200</RetailPrice>
				<IsStackable>0</IsStackable>
				<IsFromSplitter>false</IsFromSplitter>
				<WaveAllocLogRsn>45</WaveAllocLogRsn>
				<IsGift>false</IsGift>
				<AllowRevivalReceiptAlloc>0</AllowRevivalReceiptAlloc>
				<Quantity>					
					<OrderQty>&1&</OrderQty>
					<ShippedQty>!1!</ShippedQty>
					<ReceivedQty/>
					<QtyUOM>EA</QtyUOM>
				</Quantity>				
			</LineItem>};
 our $endString1 = q{    
	
	</DistributionOrder>
  </Message>
</tXML>};

	
	
#initialize an array
@items = ();
open (itemFile, $ARGV[0]); 
while(<itemFile>) {
chomp;
push(@items, $_);
}
"Items: @items\n";

#initialize an array
@shipdttm = ();
open (shipdttmFile, $ARGV[1]); 
while(<shipdttmFile>) {
chomp;
push(@shipdttm, $_);
}
"Shipdttm: @shipdttm\n";

#initialize an array
@do = ();
open (doidFile, $ARGV[2]); 
while(<doidFile>) {
chomp;
push(@do, $_);
}
"do: @do\n";

#initialize an array
@orderqty = ();
open (orderqtyFile, $ARGV[3]); 
while(<orderqtyFile>) {
chomp;
push(@orderqty, $_);
}
"orderqty: @orderqty\n";

#initialize an array
@shipqty = ();
open (shipqtyFile, $ARGV[4]); 
while(<shipqtyFile>) {
chomp;
push(@shipqty, $_);
}
"shipqty: @shipqty\n";

my $z = 0;
my $p = 0;
my $y = 0;
my $t = 0;
my $k = 0;
my $lno = 1;

for ( $count = 1; $count <=$m; $count++)	# here m is total number of DO file to be generated
{
	chomp $_;
	$locVal1 = @shipdttm[$p];
	$locVal2 = @do[$y];
 	(my $current = $template1) =~ s/#(.*?)#/$locVal2/g;
	($current = $current) =~ s/&(.*?)&/$locVal1/g;
 	open(my $fh, "> files/WHORDERS$count.xml") || die $!;
	binmode $fh;
 	print {$fh} $current;
 	$p++;
	$y++;
	 for ( $sum = 1; $sum <=$o; $sum++)	# here n is the number of Lines per DO
	{		
			$locVal3 = @items[$z];
			$locVal4 = @orderqty[$t];
			$locVal5 = @shipqty[$k];
			($current = $template3) =~ s/%(.*?)%/$lno/g;
			($current = $current) =~ s/#(.*?)#/$locVal3/g;
			($current = $current) =~ s/&(.*?)&/$locVal4/g;
			($current = $current) =~ s/!(.*?)!/$locVal5/g;
			print {$fh} $current;
			$z++;
			$t++;
			$k++;
			$lno++;
					
	}
		print {$fh} $endString1;
		close $fh;
 }	
