
$arcadiaTables = @{
    "01" = @{
        "table" = "habitats";
        "execute" = $true;
        "image" = $true
    };
    "02" = @{
        "table" = "races";
        "execute" = $true;
        "image" = $false
    };
    "03" = @{
        "table" = "animals";
        "execute" = $true;
        "image" = $true
    };
    "04" = @{
        "table" = "users";
        "execute" = $true;
        "image" = $false
    };
    "05" = @{
        "table" = "foods";
        "execute" = $true;
        "image" = $false
    };
    "06" = @{
        "table" = "veterinaryreport";
        "execute" = $true;
        "image" = $false
    };
    "07" = @{
        "table" = "services";
        "execute" = $true;
        "image" = $false
    };
    "08" = @{
        "table" = "views";
        "execute" = $true;
        "image" = $false
    };
    "09" = @{
        "table" = "hours";
        "execute" = $true;
        "image" = $false
    };
    "10" = @{
        "table" = "foodanimals";
        "execute" = $true;
        "image" = $false
    };
}

$path = ".\mocks\"

$collection = Get-Content "${path}arcadia_init.postman_collection.json" | Convertfrom-Json 

foreach ($key in $arcadiaTables.Keys | Sort-Object) {

    $arcadiaTable = $arcadiaTables.$key

    $table = $arcadiaTable.table

    if ($arcadiaTable.execute) {

        $folder = "step ${key} ${table}"

        if ($arcadiaTable.image) {

            $items = $collection.item

            $entries = @()

            foreach ($item in $collection.item) {
                if ("$($item.name)" -eq "$folder") { 
                    $entries = $item[0].item.request.body.formdata 
                }
            }

            $j = $index = -1;
            
            $entries | ForEach-Object {$j++; if ($_.src) {$index = $j}}

            $file = $entries[$index].src

            $filename = ($file -split '\/')[-1]
            
            $entries[$index].src = $file -replace ($filename, "{{imageFile}}")
            
        }

        $result = Set-Content -Pass "${path}arcadia.json" -Force -Value (ConvertTo-Json $collection -Depth 20)
        
        newman run "${path}arcadia.json" `
            -e ".\mocks\ecf-arcadia-back_env.json" `
            -d ".\mocks\${table}.json" `
            --folder "${folder}" `
            --verbose

    }
    
}
