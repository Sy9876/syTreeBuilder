# syTreeBuilder
java menu tree build using composite pattern

## data input
```
HME_00_00	home	app.home	2		10_00_00
MDM_00_00	gear	app.md	2		20_00_00
MDM_10_00	file-text-o	app.md.station	3	MDM_00_00	20_10_00
MDM_20_00	file-text-o	app.md.location	3	MDM_00_00	20_20_00
MDM_30_00	file-text-o	app.md.user	3	MDM_00_00	20_30_00
MDM_40_00	file-text-o	app.md.perm	3	MDM_00_00	20_40_00
MDM_50_00	file-text-o	app.md.dealer	3	MDM_00_00	20_50_00
CAR_00_00	truck	app.carrier	2		30_00_00
CAR_10_00	file-text-o	app.carrier.carrier	3	CAR_00_00	30_10_00
CAR_20_00	file-text-o	app.carrier.conveyance	3	CAR_00_00	30_20_00
CAR_30_00	file-text-o	app.carrier.driverfilemaintenance	3	CAR_00_00	30_30_00
```

## run

run SyTreeBuilder as java application

## json output
``` json
{
    "items": [
        {
            "title": "HME_00_00",
            "icon": "home",
            "parentId": "",
            "sref": "app.home"
        },
        {
            "title": "MDM_00_00",
            "icon": "gear",
            "parentId": "",
            "items": [
                {
                    "title": "MDM_10_00",
                    "icon": "file-text-o",
                    "parentId": "MDM_00_00",
                    "items": [
                        {
                            "title": "MDM_10_10",
                            "icon": "file-text-o",
                            "parentId": "MDM_10_00",
                            "sref": "app.md.station.xxx"
                        }
                    ],
                    "href": "#"
                },
                {
                    "title": "MDM_20_00",
                    "icon": "file-text-o",
                    "parentId": "MDM_00_00",
                    "sref": "app.md.location"
                },
                {
                    "title": "MDM_30_00",
                    "icon": "file-text-o",
                    "parentId": "MDM_00_00",
                    "sref": "app.md.user"
                },
                {
                    "title": "MDM_40_00",
                    "icon": "file-text-o",
                    "parentId": "MDM_00_00",
                    "sref": "app.md.perm"
                },
                {
                    "title": "MDM_50_00",
                    "icon": "file-text-o",
                    "parentId": "MDM_00_00",
                    "sref": "app.md.dealer"
                }
            ],
            "href": "#"
        },
        {
            "title": "CAR_00_00",
            "icon": "truck",
            "parentId": "",
            "items": [
                {
                    "title": "CAR_10_00",
                    "icon": "file-text-o",
                    "parentId": "CAR_00_00",
                    "sref": "app.carrier.carrier"
                },
                {
                    "title": "CAR_20_00",
                    "icon": "file-text-o",
                    "parentId": "CAR_00_00",
                    "sref": "app.carrier.conveyance"
                },
                {
                    "title": "CAR_30_00",
                    "icon": "file-text-o",
                    "parentId": "CAR_00_00",
                    "sref": "app.carrier.driverfilemaintenance"
                }
            ],
            "href": "#"
        }
    ]
}
```
